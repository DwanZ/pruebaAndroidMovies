package com.rappi.movies.data.source

import com.rappi.movies.data.Movies
import io.reactivex.Observable

interface MoviesDataSource {

    fun getMovies(listId:String,page:String?): Observable<Movies>

}