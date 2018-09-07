package com.rappi.movies.upcoming

import com.rappi.movies.BaseContract
import com.rappi.movies.data.Movie

class UpcomingContract {
    interface Presenter: BaseContract.Presenter{
        fun onSearchPressed(query:String)
        fun onGetMovies()
    }
    interface View: BaseContract.View<Presenter>{
        fun hideProcessingMessage()
        fun showProcessingMessage()
        fun showEmptyListMessage()
        fun showErrorMessage(error:String)
        fun showUpcomingMovies(t:MutableList<Movie>)
        fun showMovieDetail(m: Movie)
        fun showQueryResult(query:String)
    }
}