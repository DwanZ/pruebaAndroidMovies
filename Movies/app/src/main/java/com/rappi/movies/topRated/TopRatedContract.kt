package com.rappi.movies.topRated

import com.rappi.movies.BaseContract
import com.rappi.movies.data.Movie

class TopRatedContract {
    interface Presenter: BaseContract.Presenter{
        fun onSearchPressed(query:String)
        fun onGetMovies()
    }
    interface View: BaseContract.View<Presenter>{
        fun hideProcessingMessage()
        fun showProcessingMessage()
        fun showEmptyListMessage()
        fun showErrorMessage(error:String)
        fun showTopRatedMovies(t:MutableList<Movie>)
        fun showMovieDetail(m: Movie)
        fun showQueryResult(query:String)
    }
}