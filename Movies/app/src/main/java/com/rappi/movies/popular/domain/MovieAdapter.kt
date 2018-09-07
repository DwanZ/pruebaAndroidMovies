package com.rappi.movies.popular.domain

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.rappi.movies.data.Movie
import android.view.View
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import com.rappi.movies.R
import com.squareup.picasso.Picasso
class MovieAdapter(val context: Context, private val onMovieClicked: (movie: Movie) -> Unit): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var movieList: MutableList<Movie>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.movie_card_view, parent, false))
    }

    override fun getItemCount(): Int {
        return movieList?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList!![position]
        holder.textViewTitle!!.text = movie.title
        holder.textViewAverage!!.text = movie.vote_average.toString()
        holder.binImage(movie)
        holder.itemView.setOnClickListener {
            onMovieClicked(movie)
        }
    }

    fun setData(request: MutableList<Movie>) {
        movieList = request
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textViewTitle: TextView? = itemView.findViewById(R.id.title) as TextView
        val textViewAverage: TextView? = itemView.findViewById(R.id.average) as TextView
        val image :ImageView = itemView.findViewById(R.id.thumbnail) as ImageView

        fun binImage(movie: Movie) {
            Picasso.with(context).load("https://image.tmdb.org/t/p/w200"+movie.poster_path).into(image)
        }
    }
}