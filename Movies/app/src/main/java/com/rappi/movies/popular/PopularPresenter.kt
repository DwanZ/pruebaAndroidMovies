package com.rappi.movies.popular

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.rappi.movies.data.Movies
import com.rappi.movies.popular.domain.usecase.ParamsRequestValue
import com.rappi.movies.popular.domain.usecase.PopularUseCase
import com.rappi.movies.utils.exceptions.ErrorMessageFactory
import io.reactivex.observers.DisposableSingleObserver

class PopularPresenter(private val mView:PopularContract.View,
                       private val popularUseCase:PopularUseCase,
                       private val errorMessageFactory: ErrorMessageFactory):PopularContract.Presenter {

    init {
        mView.setPresenter(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun subscribe() {
        mView.showProcessingMessage()
        popularUseCase.execute(GetPopularMoviesObserver(),ParamsRequestValue("1",null))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun unsubscribe() {
        popularUseCase.unsubscribe()
    }

    override fun onSearchPressed(query: String) {
        mView.showQueryResult(query)
    }
    override fun onGetMovies() {
        mView.showProcessingMessage()
        popularUseCase.execute(GetPopularMoviesObserver(),ParamsRequestValue("1",null))
    }
    private inner class GetPopularMoviesObserver : DisposableSingleObserver<Movies>() {
        override fun onSuccess(t: Movies) {
            mView.hideProcessingMessage()
            if(t.results == null){
                mView.showEmptyListMessage()
            }
            mView.showPopularMovies(t.results!!)
        }
        override fun onError(e: Throwable) {
            mView.hideProcessingMessage()
            mView.showErrorMessage(errorMessageFactory.create(e))
        }
    }
}