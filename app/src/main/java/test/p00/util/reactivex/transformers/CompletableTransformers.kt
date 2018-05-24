package test.p00.util.reactivex.transformers

import io.reactivex.CompletableTransformer
import test.p00.util.reactivex.schedulers.Schedulers

object CompletableTransformers {

    fun schedulers(scheduler: Schedulers = Schedulers.create()) =
            CompletableTransformer {
                it.observeOn(scheduler.ui())
                  .subscribeOn(scheduler.background()) }

}