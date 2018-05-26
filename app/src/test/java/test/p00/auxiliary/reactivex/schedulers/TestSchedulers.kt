package test.p00.auxiliary.reactivex.schedulers

import io.reactivex.Scheduler

/**
 * Created by Peter Bukhal on 5/27/18.
 */
class TestSchedulers : Schedulers {

    override fun ui(): Scheduler = io.reactivex.schedulers.Schedulers.trampoline()
    override fun background(): Scheduler = io.reactivex.schedulers.Schedulers.trampoline()

}