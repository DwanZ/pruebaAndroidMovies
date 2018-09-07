@file:Suppress("DEPRECATION")

package com.rappi.movies.upcoming


import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rappi.movies.BaseFragment

import com.rappi.movies.R
import com.rappi.movies.data.Movie
import com.rappi.movies.popular.domain.MovieAdapter
import kotlinx.android.synthetic.main.fragment_upcoming.*


class UpcomingFragment : BaseFragment<UpcomingContract.Presenter>(),UpcomingContract.View {

    private val adapter by lazy { MovieAdapter(activity!!.applicationContext) { movie -> showMovieDetail(movie) } }
    private var progressDialog: ProgressDialog? = null
    private var globalMovieList: MutableList<Movie> = arrayListOf()
    private var recycleGroup: RecyclerView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_upcoming, container, false)
        recycleGroup = root.findViewById(R.id.recycleUpcomingMovies)
        recycleGroup!!.layoutManager = GridLayoutManager(this@UpcomingFragment.context, 2)
        recycleGroup!!.adapter = adapter
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchMovieUpcomingButton.setOnClickListener {
            mPresenter.onSearchPressed(searchMovieUpcoming.text.toString())
        }
    }
    override fun hideProcessingMessage() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    override fun showProcessingMessage() {
        progressDialog = ProgressDialog(context,R.style.AppCompatAlertDialogStyle)
        progressDialog!!.setTitle("Info")
        progressDialog!!.setMessage("Loading data")
        progressDialog!!.isIndeterminate = true
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }

    override fun showEmptyListMessage() {
        AlertDialog.Builder(context!!)
                .setTitle("Sorry")
                .setMessage("There are not movies")
                .setNegativeButton("Ok") { /* nothing */ _, _ -> }
                .show()
    }

    override fun showErrorMessage(error: String) {
        AlertDialog.Builder(context!!)
                .setTitle("Sorry")
                .setMessage("error")
                .setNegativeButton("Ok") { /* nothing */ _, _ -> }
                .show()
    }

    override fun showQueryResult(query: String) {
        adapter.setData(globalMovieList)
        if(query.isEmpty()){
            mPresenter.onGetMovies()
        }else{
            val filtered = adapter.movieList!!.filter { m -> m.title.contains(query,true)  }
            adapter.setData(filtered.toMutableList())
        }
    }
    override fun showUpcomingMovies(t: MutableList<Movie>) {
        globalMovieList = t.toMutableList()
        adapter.setData(t.toMutableList())
    }
    override fun showMovieDetail(m: Movie) {
        (activity as UpcomingActivity).showMovieDetail(m)
    }

    companion object {
        fun newInstance(): UpcomingFragment {
            return UpcomingFragment()
        }
    }
}