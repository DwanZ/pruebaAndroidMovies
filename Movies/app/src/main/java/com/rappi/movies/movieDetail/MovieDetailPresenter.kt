package com.rappi.movies.movieDetail

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import com.rappi.movies.movieDetail.domain.usecase.GetYoutubeKeyUseCase
import com.rappi.movies.utils.exceptions.ErrorMessageFactory

class MovieDetailPresenter(private val mView:MovieDetailContract.View,
                           //private val getYoutubeKeyUseCase: GetYoutubeKeyUseCase,
                           private val errorMessageFactory: ErrorMessageFactory):MovieDetailContract.Presenter {

    init {
        mView.setPresenter(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun subscribe() {
        //noting by context
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun unsubscribe() {
        //noting by context
    }

}