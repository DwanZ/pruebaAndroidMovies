package com.rappi.movies.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Movies(var page: Int? = null,
        var total_results: Int? = null,
        var total_pages: Int? = null,
        var results: MutableList<Movie>? = null): Serializable {
}