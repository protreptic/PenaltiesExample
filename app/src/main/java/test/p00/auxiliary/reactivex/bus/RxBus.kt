package test.p00.auxiliary.reactivex.bus

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Peter Bukhal on 5/20/18.
 */
object RxBus {

    private val bus = PublishSubject.create<Any>()

    fun sendEvent(event: Any) { bus.onNext(event) }
    fun subscribe(block: (Any) -> Unit): Disposable = bus.subscribe({ block(it) })

}