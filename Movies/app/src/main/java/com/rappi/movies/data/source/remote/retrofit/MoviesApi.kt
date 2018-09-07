package com.rappi.movies.data.source.remote.retrofit

import com.rappi.movies.data.Movies
import io.reactivex.Observable
import retrofit2.http.*

interface MoviesApi {

    @GET("list/{list_id}")
    fun getMovies(@Path("list_id") listId:String,@Query("page") page: String): Observable<Movies>

    @GET("list/{list_id}")
    fun getMovies(@Path("list_id") listId:String): Observable<Movies>

    companion object {
        val AUTH_HEADER = "Authorization"
    }
}
