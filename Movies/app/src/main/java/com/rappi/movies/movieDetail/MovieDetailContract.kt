package com.rappi.movies.movieDetail

import com.rappi.movies.BaseContract
import com.rappi.movies.data.Movie

class MovieDetailContract {

    interface Presenter: BaseContract.Presenter{

    }
    interface View: BaseContract.View<Presenter>{
        fun showMovieInfo(movie:Movie)
        fun renderVideo(url:String)
    }
}