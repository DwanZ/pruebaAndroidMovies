package com.rappi.movies.popular.domain.usecase

import com.rappi.movies.UseCase
import com.rappi.movies.data.Movies
import com.rappi.movies.data.source.MoviesRepository
import com.rappi.movies.utils.schedulers.BaseSchedulerProvider
import io.reactivex.Observable

class PopularUseCase(schedulerProvider: BaseSchedulerProvider,
                     private val moviesRepository: MoviesRepository): UseCase<Movies>(schedulerProvider)  {

    override fun createObservable(): Observable<Movies> {
        if (mRequestValue == null) throw IllegalArgumentException()
        if (mRequestValue !is ParamsRequestValue) throw IllegalArgumentException()

        val data = mRequestValue as ParamsRequestValue
        return moviesRepository.getMovies(data.listId,null)
    }


}