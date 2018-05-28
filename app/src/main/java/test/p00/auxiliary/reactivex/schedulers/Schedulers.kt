package test.p00.auxiliary.reactivex.schedulers

import io.reactivex.Scheduler

interface Schedulers {

    companion object {

        fun schedulers(): Schedulers = PrimarySchedulers()

    }

    /**
     *
     */
    fun ui(): Scheduler

    /**
     *
     */
    fun background(): Scheduler

}