package test.p00.presentation.signin.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import test.p00.R
import test.p00.presentation.impl.abs.AbsView
import test.p00.presentation.signin.SignInPresenter
import test.p00.presentation.signin.SignInView

/**
 * Created by Peter Bukhal on 5/12/18.
 */
class SignInFragment : AbsView(), SignInView {

    companion object {

        const val FRAGMENT_TAG = "tag_SignInFragment"

        fun newInstance(): Fragment = SignInFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    private val presenter: SignInPresenter by lazy {
        SignInPresenterImpl()
    }

    override val targetLayout = R.layout.view_sign_in

}