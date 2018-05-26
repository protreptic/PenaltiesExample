package test.p00.auxiliary.bus

import io.reactivex.disposables.Disposable

/**
 * Created by Peter Bukhal on 5/27/18.
 */
interface Bus {

    /**
     *
     */
    fun sendEvent(event: BusEvent)

    /**
     *
     */
    fun subscribe(block: (event: BusEvent) -> Unit): Disposable

}