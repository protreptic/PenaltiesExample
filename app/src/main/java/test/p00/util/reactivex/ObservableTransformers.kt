package test.p00.util.reactivex

import io.reactivex.ObservableTransformer

object ObservableTransformers {

    fun <Any> schedulers(scheduler: Schedulers = Schedulers.create()) =
            ObservableTransformer<Any, Any> {
                it.observeOn(scheduler.ui())
                  .subscribeOn(scheduler.background()) }

}