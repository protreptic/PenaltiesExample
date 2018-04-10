package test.p00.util.reactivex

import io.reactivex.Scheduler

interface Schedulers {

    companion object {

        fun create(): Schedulers = PrimarySchedulers()

    }

    fun ui(): Scheduler
    fun background(): Scheduler

}