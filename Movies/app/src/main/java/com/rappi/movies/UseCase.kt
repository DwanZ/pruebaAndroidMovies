package com.rappi.movies


import com.rappi.movies.utils.schedulers.BaseSchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver

/**
 * @author DwanZ
 *
 * Base UseCase of the application. The use cases are used to retrieve and send mInventoryRequests to the server
 * and processing it so the presenters don't have to deal with any kind of business logic.
 */
abstract class UseCase<T> protected constructor(val mSchedulerProvider: BaseSchedulerProvider) : Interactor<T> {

    /**
     * Optional value that can be used into the #createObservable method. This can be set from the
     * #UseCase launcher
     */
    protected var mRequestValue: RequestValue? = null

    /**
     * Disposables subscribed to the  #Observable from the #createObservable method. It is necessary
     * to unsubscribe them once the UseCase is not longer used to stop any kind of unfinished task.
     */
    private var disposables = Disposables.empty()

    /**
     * Abstract method that emits the observable that will execute this #UseCase.
     */
    protected abstract fun createObservable(): Observable<T>

    /**
     * Immediately executes this #UseCase with the given #DisposableObserver
     *
     * @param observer The observer who subscribes to this #UseCase
     */
    override fun execute(observer: DisposableObserver<T>) {
        disposables = createObservable()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribeWith(observer)
    }

    /**
     * Immediately executes this #UseCase with the given #DisposableSingleObserver
     *
     * @param observer The observer who subscribes to this #UseCase
     */
    override fun execute(observer: DisposableSingleObserver<T>) {
        disposables = Single.fromObservable(createObservable())
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribeWith(observer)
    }

    /**
     * Immediately executes this #UseCase with the given #DisposableObserver
     *
     * @param observer          The observer who subscribes to this #UseCase
     * @param requestValue      The request value if required by the #UseCase implementation
     */
    override fun execute(observer: DisposableObserver<T>, requestValue: RequestValue) {
        mRequestValue = checkNotNull(requestValue)
        disposables = createObservable()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribeWith(observer)
    }

    /**
     * Immediately executes this #UseCase with the given #DisposableSingleObserver
     *
     * @param observer          The observer who subscribes to this #UseCase
     * @param requestValue      The request value if required by the #UseCase implementation
     */
    override fun execute(observer: DisposableSingleObserver<T>, requestValue: RequestValue) {
        mRequestValue = checkNotNull(requestValue)
        disposables = Single.fromObservable(createObservable())
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribeWith(observer)
    }

    /**
     * Returns the #Observable of this #UseCase but does not execute it. It must be executed
     * manually.
     */
    override fun execute(): Observable<T> {
        return createObservable()
    }

    /**
     * Unsubscribes this #UseCase #disposables
     */
    fun unsubscribe() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    /**
     * This method allows to set the #requestValue to this #UseCase
     */
    fun setRequestValue(requestValue: RequestValue) {
        mRequestValue = checkNotNull(requestValue)
    }

    /**
     * This interface must be implemented from any class that want to be used as the #requestValue
     * of an #UseCase
     */
    interface RequestValue
}
