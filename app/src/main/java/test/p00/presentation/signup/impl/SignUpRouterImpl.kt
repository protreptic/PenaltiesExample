package test.p00.presentation.signup.impl

import android.support.v4.app.FragmentManager
import test.p00.presentation.abs.Router
import test.p00.presentation.signup.SignUpRouter

/**
 * Created by Peter Bukhal on 5/12/18.
 */
class SignUpRouterImpl(
        override val fragmentManager: FragmentManager?,
        override val delegate: Router.Delegate): SignUpRouter