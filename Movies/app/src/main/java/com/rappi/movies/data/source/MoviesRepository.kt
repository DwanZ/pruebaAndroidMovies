package com.rappi.movies.data.source

import android.content.Context
import com.rappi.movies.data.Movies
import com.rappi.movies.data.source.remote.MoviesRemoteDataSource.Companion.moviesRemoteDataSource
import io.reactivex.Observable

class MoviesRepository (private val mRemoteDataSource: MoviesDataSource):MoviesDataSource{
    override fun getMovies(listId: String, page: String?): Observable<Movies> {
        return mRemoteDataSource.getMovies(listId, page)
    }

    companion object {
        private var INSTANCE: MoviesRepository? = null
        fun Context.moviesRepository(): MoviesRepository {
            if (INSTANCE == null) {
                INSTANCE = MoviesRepository(mRemoteDataSource = moviesRemoteDataSource())
            }
            return INSTANCE!!
        }
    }
}