package test.p00.presentation.signup.verification.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.auxiliary.reactivex.schedulers.Schedulers
import test.p00.auxiliary.reactivex.transformers.ObservableTransformers
import test.p00.domain.signup.SignUpInteractor
import test.p00.presentation.signup.SignUpRouter
import test.p00.presentation.signup.verification.SignUpVerificationPresenter
import test.p00.presentation.signup.verification.SignUpVerificationView

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class SignUpVerificationPresenterImpl(
        private val schedulers: Schedulers,
        private val interactor: SignUpInteractor,
        private val router: SignUpRouter):
            SignUpVerificationPresenter {

    override var attachedView: SignUpVerificationView? = null
    override var disposables = CompositeDisposable()

    override fun verifyCode(confirmationCode: String) {
        disposables.add(
            interactor
                .confirmCode(confirmationCode)
                .compose(ObservableTransformers.schedulers(schedulers))
                .subscribe(
                    { router.toLauncher() },
                    { router.toPhone() }))
    }

}