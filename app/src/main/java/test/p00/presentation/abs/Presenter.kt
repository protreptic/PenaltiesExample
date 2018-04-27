package test.p00.presentation.abs

import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable

interface Presenter<T: View> {

    var attachedView: T
    val disposables: CompositeDisposable

    @CallSuper
    fun attachView(view: T) {
        attachedView = view
    }

    fun detachView() {
        disposables.dispose()
    }

}