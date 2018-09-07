package com.rappi.movies.data.source.remote

import android.content.Context
import com.rappi.movies.data.Movies
import com.rappi.movies.data.source.MoviesDataSource
import com.rappi.movies.data.source.remote.retrofit.MoviesApi
import com.rappi.movies.data.source.remote.retrofit.MoviesApiBuilder.Companion.moviesApi
import io.reactivex.Observable

class MoviesRemoteDataSource(private val moviesApi: MoviesApi): MoviesDataSource {
    override fun getMovies(listId: String, page: String?): Observable<Movies> {
        return if(page == null) moviesApi.getMovies(listId)else moviesApi.getMovies(listId,page)
    }

    companion object {
        private var INSTANCE: MoviesRemoteDataSource? = null
        fun Context.moviesRemoteDataSource(): MoviesRemoteDataSource {
            if (INSTANCE == null) {
                INSTANCE = MoviesRemoteDataSource(moviesApi())
            }
            return INSTANCE!!
        }
    }
}