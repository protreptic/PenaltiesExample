package test.p00.presentation.signin.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.presentation.Router
import test.p00.presentation.signin.SignInPresenter
import test.p00.presentation.signin.SignInRouter
import test.p00.presentation.signin.SignInView

/**
 * Created by Peter Bukhal on 5/12/18.
 */
class SignInPresenterImpl : SignInPresenter {

    override var attachedView: SignInView? = null
    override var disposables = CompositeDisposable()

}