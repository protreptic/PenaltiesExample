package test.p00.presentation.signup.verification.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.presentation.signup.SignUpRouter
import test.p00.presentation.signup.verification.SignUpVerificationPresenter
import test.p00.presentation.signup.verification.SignUpVerificationView

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class SignUpVerificationPresenterImpl(
        private val router: SignUpRouter):
        SignUpVerificationPresenter {

    override var attachedView: SignUpVerificationView? = null
    override var disposables = CompositeDisposable()

    override fun attachView(view: SignUpVerificationView) {
        super.attachView(view)


    }

}