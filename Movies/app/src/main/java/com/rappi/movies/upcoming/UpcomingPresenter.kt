package com.rappi.movies.upcoming

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.rappi.movies.data.Movies
import com.rappi.movies.popular.domain.usecase.ParamsRequestValue
import com.rappi.movies.upcoming.domain.usecase.UpcomingUseCase
import com.rappi.movies.utils.exceptions.ErrorMessageFactory
import io.reactivex.observers.DisposableSingleObserver

class UpcomingPresenter(private val mView:UpcomingContract.View,
                        private val upcomingUseCase: UpcomingUseCase,
                        private val errorMessageFactory: ErrorMessageFactory):UpcomingContract.Presenter {

    init {
        mView.setPresenter(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun subscribe() {
        mView.showProcessingMessage()
        upcomingUseCase.execute(GetTopRatedMoviesObserver(), ParamsRequestValue("200","1"))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun unsubscribe() {
        upcomingUseCase.unsubscribe()
    }
    override fun onSearchPressed(query: String) {
        mView.showQueryResult(query)
    }
    override fun onGetMovies() {
        mView.showProcessingMessage()
        upcomingUseCase.execute(GetTopRatedMoviesObserver(),ParamsRequestValue("1",null))
    }
    private inner class GetTopRatedMoviesObserver : DisposableSingleObserver<Movies>() {
        override fun onSuccess(t: Movies) {
            mView.hideProcessingMessage()
            if(t.results == null){
                mView.showEmptyListMessage()
            }
            mView.showUpcomingMovies(t.results!!)
        }
        override fun onError(e: Throwable) {
            mView.hideProcessingMessage()
            mView.showErrorMessage(errorMessageFactory.create(e))
        }
    }
}