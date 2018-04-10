package test.p00.util.reactivex

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class PrimarySchedulers : test.p00.util.reactivex.Schedulers {

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun background(): Scheduler = io.reactivex.schedulers.Schedulers.computation()

}