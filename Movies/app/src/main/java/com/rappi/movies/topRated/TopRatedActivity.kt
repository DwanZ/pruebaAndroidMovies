package com.rappi.movies.topRated

import android.os.Bundle
import com.rappi.movies.R
import com.rappi.movies.data.Movie
import com.rappi.movies.data.source.MoviesRepository.Companion.moviesRepository
import com.rappi.movies.introPager.DrawerActivity
import com.rappi.movies.movieDetail.MovieDetailFragment
import com.rappi.movies.movieDetail.MovieDetailPresenter
import com.rappi.movies.topRated.domain.usecase.TopRatedUseCase
import com.rappi.movies.utils.ActivityUtils
import com.rappi.movies.utils.exceptions.ErrorMessageFactory.Companion.errorMessageFactory
import com.rappi.movies.utils.schedulers.SchedulerProvider

class TopRatedActivity : DrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.title = "Top Rated Movies"
        }
        var fragment: TopRatedFragment? = supportFragmentManager
                .findFragmentById(R.id.contentActivity) as TopRatedFragment?

        if (fragment == null) {
            fragment = TopRatedFragment.newInstance()
            ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment, R.id.contentFragment)
        }

        val get = TopRatedUseCase(
                SchedulerProvider.schedulerProvider(),
                moviesRepository())

        TopRatedPresenter(fragment,get,errorMessageFactory())
    }

    fun showMovieDetail(movie: Movie){
        val bundle = Bundle()
        bundle.putSerializable("movie", movie)
        val fd = MovieDetailFragment.newInstance()
        fd.arguments = bundle

        ActivityUtils.replaceFragmentInActivity(supportFragmentManager, fd, R.id.contentFragment,fd.TAG_FRAGMENT)

        MovieDetailPresenter(fd,errorMessageFactory())
    }
}
