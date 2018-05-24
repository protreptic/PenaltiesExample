package test.p00.util.reactivex.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class PrimarySchedulers : Schedulers {

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun background(): Scheduler = io.reactivex.schedulers.Schedulers.computation()

}