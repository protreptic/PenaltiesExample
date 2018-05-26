package test.p00.auxiliary.bus

import test.p00.auxiliary.reactivex.bus.RxBus

/**
 * Created by Peter Bukhal on 5/27/18.
 */
object BusFactory {

    /**
     *
     */
    fun bus(): Bus = RxBus()

}