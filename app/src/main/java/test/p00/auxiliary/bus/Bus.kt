package test.p00.auxiliary.bus

import io.reactivex.disposables.Disposable
import test.p00.auxiliary.reactivex.bus.RxBus

/**
 * Created by Peter Bukhal on 5/27/18.
 */
interface Bus {

    companion object {

        fun bus(): Bus = RxBus()

    }

    /**
     *
     */
    fun sendEvent(event: BusEvent)

    /**
     *
     */
    fun subscribe(block: (event: BusEvent) -> Unit): Disposable

}