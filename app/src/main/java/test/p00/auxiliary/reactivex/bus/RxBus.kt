package test.p00.auxiliary.reactivex.bus

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import test.p00.auxiliary.bus.Bus
import test.p00.auxiliary.bus.BusEvent

/**
 * Created by Peter Bukhal on 5/27/18.
 */
class RxBus : Bus {

    private val bus = PublishSubject.create<BusEvent>()

    override fun sendEvent(event: BusEvent) =
            bus.onNext(event)

    override fun subscribe(block: (event: BusEvent) -> Unit): Disposable =
            bus.subscribe({ event -> block(event) }, { /* игнорируем */ })

}