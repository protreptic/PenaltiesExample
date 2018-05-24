package test.p00.presentation.signup.verification.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.signup.SignUpInteractor
import test.p00.presentation.signup.SignUpRouter
import test.p00.presentation.signup.verification.SignUpVerificationPresenter
import test.p00.presentation.signup.verification.SignUpVerificationView
import test.p00.util.reactivex.transformers.ObservableTransformers
import test.p00.util.reactivex.schedulers.Schedulers

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class SignUpVerificationPresenterImpl(
        private val scheduler: Schedulers = Schedulers.create(),
        private val interactor: SignUpInteractor = SignUpInteractor(),
        private val router: SignUpRouter):
        SignUpVerificationPresenter {

    override var attachedView: SignUpVerificationView? = null
    override var disposables = CompositeDisposable()

    override fun verifyCode(confirmationCode: String) {
        disposables.add(
            interactor
                .confirmCode(confirmationCode.toInt())
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe(
                    { router.toLauncher() },
                    { router.toPhone() }))
    }

}