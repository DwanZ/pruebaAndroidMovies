package com.rappi.movies

import android.support.v4.app.Fragment

open class BaseFragment<T : BaseContract.Presenter> : Fragment(){


    protected lateinit var mPresenter: T

    fun setPresenter(presenter: T) {
        mPresenter = presenter
        lifecycle.addObserver(mPresenter)
    }
}