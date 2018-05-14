package test.p00.presentation.signup.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.presentation.signup.SignUpPresenter
import test.p00.presentation.signup.SignUpRouter
import test.p00.presentation.signup.SignUpView

/**
 * Created by Peter Bukhal on 5/12/18.
 */
class SignUpPresenterImpl(
        private val router: SignUpRouter):
        SignUpPresenter {

    override var attachedView: SignUpView? = null
    override var disposables = CompositeDisposable()

    override fun attachView(view: SignUpView) {
        super.attachView(view)


    }

}