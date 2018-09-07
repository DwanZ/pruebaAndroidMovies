package com.rappi.movies.popular

import android.os.Bundle
import com.rappi.movies.R
import com.rappi.movies.data.Movie
import com.rappi.movies.data.source.MoviesRepository.Companion.moviesRepository
import com.rappi.movies.introPager.DrawerActivity
import com.rappi.movies.movieDetail.MovieDetailFragment
import com.rappi.movies.movieDetail.MovieDetailPresenter
import com.rappi.movies.popular.domain.usecase.PopularUseCase
import com.rappi.movies.utils.ActivityUtils
import com.rappi.movies.utils.exceptions.ErrorMessageFactory.Companion.errorMessageFactory
import com.rappi.movies.utils.schedulers.SchedulerProvider.Companion.schedulerProvider

class PopularActivity : DrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_popular)
        if (supportActionBar != null) {
            supportActionBar!!.title = "Popular Movies"
        }
        var fragment: PopularFragment? = supportFragmentManager
                .findFragmentById(R.id.contentActivity) as PopularFragment?

        if (fragment == null) {
            fragment = PopularFragment.newInstance()
            ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment, R.id.contentFragment)
        }

        val get = PopularUseCase(
                schedulerProvider(),
                moviesRepository())

        PopularPresenter(fragment,get,errorMessageFactory())
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
