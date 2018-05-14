package test.p00.presentation.signup.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import test.p00.R
import test.p00.presentation.activity.abs.AbsFragment
import test.p00.presentation.signup.SignUpPresenter
import test.p00.presentation.signup.SignUpView

/**
 * Created by Peter Bukhal on 5/12/18.
 */
class SignUpFragment : AbsFragment(), SignUpView {

    companion object {

        const val FRAGMENT_TAG = "tag_SignUpFragment"

        fun newInstance(): Fragment = SignUpFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    private val presenter: SignUpPresenter by lazy {
        SignUpPresenterImpl(router = SignUpRouterImpl(fragmentManager, this))
    }

    override val targetLayout = R.layout.view_sign_up

}