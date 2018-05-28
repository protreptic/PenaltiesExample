package test.p00.auxiliary.reactivex.transformers

import io.reactivex.CompletableTransformer
import test.p00.auxiliary.reactivex.schedulers.Schedulers

object CompletableTransformers {

    fun schedulers(scheduler: Schedulers) =
            CompletableTransformer {
                it.observeOn(scheduler.ui())
                  .subscribeOn(scheduler.background()) }

}