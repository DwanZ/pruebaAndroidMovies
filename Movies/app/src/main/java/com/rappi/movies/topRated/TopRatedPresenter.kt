package com.rappi.movies.topRated

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.rappi.movies.data.Movies
import com.rappi.movies.popular.domain.usecase.ParamsRequestValue
import com.rappi.movies.topRated.domain.usecase.TopRatedUseCase
import com.rappi.movies.utils.exceptions.ErrorMessageFactory
import io.reactivex.observers.DisposableSingleObserver

class TopRatedPresenter(private val mView:TopRatedContract.View,
                        private val topRatedUseCase: TopRatedUseCase,
                        private val errorMessageFactory: ErrorMessageFactory):TopRatedContract.Presenter {

    init {
        mView.setPresenter(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun subscribe() {
        mView.showProcessingMessage()
        topRatedUseCase.execute(GetTopRatedMoviesObserver(), ParamsRequestValue("3","1"))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun unsubscribe() {
        topRatedUseCase.unsubscribe()
    }
    override fun onSearchPressed(query: String) {
        mView.showQueryResult(query)
    }
    override fun onGetMovies() {
        mView.showProcessingMessage()
        topRatedUseCase.execute(GetTopRatedMoviesObserver(),ParamsRequestValue("1",null))
    }
    private inner class GetTopRatedMoviesObserver : DisposableSingleObserver<Movies>() {
        override fun onSuccess(t: Movies) {
            mView.hideProcessingMessage()
            if(t.results == null){
                mView.showEmptyListMessage()
            }
            mView.showTopRatedMovies(t.results!!)
        }
        override fun onError(e: Throwable) {
            mView.hideProcessingMessage()
            mView.showErrorMessage(errorMessageFactory.create(e))
        }
    }
}