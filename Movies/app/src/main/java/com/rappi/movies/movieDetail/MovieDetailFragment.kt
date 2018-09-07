package com.rappi.movies.movieDetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.rappi.movies.BaseFragment
import com.rappi.movies.R
import com.rappi.movies.data.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : BaseFragment<MovieDetailContract.Presenter>(),MovieDetailContract.View {


    val TAG_FRAGMENT = "com.rappi.movies.movieDetail.MovieDetailFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie:Movie= arguments!!.getSerializable("movie") as Movie
        showMovieInfo(movie)
    }

    override fun showMovieInfo(movie: Movie) {
        detailTitle.text = movie.title
        detailDuration.text = movie.release_date
        detailOverview.text = movie.overview
        detailAverage.text = movie.vote_average.toString()
        detailCount.text = movie.vote_count.toString()
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500"+movie.poster_path).into(detailPoster as ImageView)
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500"+movie.backdrop_path).into(detailBackdrop as ImageView)
    }
    override fun renderVideo(url: String) {

    }
    companion object {
        fun newInstance(): MovieDetailFragment {
            return MovieDetailFragment()
        }
    }

}
