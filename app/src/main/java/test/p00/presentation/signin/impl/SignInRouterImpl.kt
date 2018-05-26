package test.p00.presentation.signin.impl

import android.support.v4.app.FragmentManager
import test.p00.presentation.Router
import test.p00.presentation.signin.SignInRouter

/**
 * Created by Peter Bukhal on 5/12/18.
 */
class SignInRouterImpl(
        override val fragmentManager: FragmentManager?,
        override val delegate: Router.Delegate):
        SignInRouter