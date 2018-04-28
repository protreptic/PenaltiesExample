package test.p00.presentation.abs

import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable

interface Presenter<T: View> {

    var attachedView: T?
    var disposables: CompositeDisposable

    @CallSuper
    fun attachView(view: T) {
        attachedView = view
        disposables = CompositeDisposable()
    }

    fun detachView() {
        disposables.dispose()
        attachedView = null
    }

}