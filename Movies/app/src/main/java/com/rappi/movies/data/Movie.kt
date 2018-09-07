package com.rappi.movies.data

import java.io.Serializable

class Movie(val id:Long,
            val vote_average:Double,
            val vote_count:Int,
            val title:String,
            val popularity:Double,
            val release_date:String,
            val poster_path:String,
            val backdrop_path:String,
            val overview:String):Serializable {

}