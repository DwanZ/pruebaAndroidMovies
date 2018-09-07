package com.rappi.movies.upcoming

import android.os.Bundle
import com.rappi.movies.R
import com.rappi.movies.data.Movie
import com.rappi.movies.data.source.MoviesRepository.Companion.moviesRepository
import com.rappi.movies.introPager.DrawerActivity
import com.rappi.movies.movieDetail.MovieDetailFragment
import com.rappi.movies.movieDetail.MovieDetailPresenter
import com.rappi.movies.upcoming.domain.usecase.UpcomingUseCase
import com.rappi.movies.utils.ActivityUtils
import com.rappi.movies.utils.exceptions.ErrorMessageFactory.Companion.errorMessageFactory
import com.rappi.movies.utils.schedulers.SchedulerProvider

class UpcomingActivity : DrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.title = "Upcoming Movies"
        }
        var fragment: UpcomingFragment? = supportFragmentManager
                .findFragmentById(R.id.contentActivity) as UpcomingFragment?

        if (fragment == null) {
            fragment = UpcomingFragment.newInstance()
            ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment, R.id.contentFragment)
        }

        val get = UpcomingUseCase(
                SchedulerProvider.schedulerProvider(),
                moviesRepository())

        UpcomingPresenter(fragment,get,errorMessageFactory())
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
