package test.p00.auxiliary.reactivex.transformers

import io.reactivex.ObservableTransformer
import test.p00.auxiliary.reactivex.schedulers.Schedulers

object ObservableTransformers {

    fun <Any> schedulers(scheduler: Schedulers = Schedulers.create()) =
            ObservableTransformer<Any, Any> {
                it.observeOn(scheduler.ui())
                  .subscribeOn(scheduler.background()) }

}