package test.p00.util.reactivex.transformers

import io.reactivex.ObservableTransformer
import test.p00.util.reactivex.schedulers.Schedulers

object ObservableTransformers {

    fun <Any> schedulers(scheduler: Schedulers = Schedulers.create()) =
            ObservableTransformer<Any, Any> {
                it.observeOn(scheduler.ui())
                  .subscribeOn(scheduler.background()) }

}