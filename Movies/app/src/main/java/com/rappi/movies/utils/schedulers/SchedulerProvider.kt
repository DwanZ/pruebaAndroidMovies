package com.rappi.movies.utils.schedulers

import com.rappi.movies.utils.schedulers.BaseSchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider : BaseSchedulerProvider {

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    companion object {

        private var INSTANCE: SchedulerProvider? = null

        fun schedulerProvider(): BaseSchedulerProvider {
            if (INSTANCE == null) {
                INSTANCE = SchedulerProvider()
            }
            return INSTANCE as SchedulerProvider
        }
    }
}
