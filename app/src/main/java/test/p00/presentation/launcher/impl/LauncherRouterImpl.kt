package test.p00.presentation.launcher.impl

import android.support.v4.app.FragmentManager
import test.p00.presentation.abs.Router.Delegate
import test.p00.presentation.launcher.LauncherRouter
import test.p00.presentation.onboarding.impl.OnBoardingFragment
import test.p00.presentation.onboarding.wizard.impl.OnBoardingWizardFragment
import test.p00.presentation.signup.impl.SignUpFragment

/**
 * Created by Peter Bukhal on 4/23/18.
 */
class LauncherRouterImpl(
        override val fragmentManager: FragmentManager?,
        override val delegate: Delegate) : LauncherRouter {

//    override fun toHome() {
//        super.toHome()
//
//        if (delegate.checkIfRoutingAvailable()) {
//            fragmentManager
//                    ?.beginTransaction()
//                    ?.setCustomAnimations(
//                            0, R.anim.slide_out_right,
//                            0, R.anim.slide_out_right)
//                    ?.add(android.R.id.content,
//                            ConversationsFragment.newInstance(),
//                            ConversationsFragment.FRAGMENT_TAG)
//                    ?.addToBackStack(ConversationsFragment.FRAGMENT_TAG)
//                    ?.commit()
//
//            fragmentManager
//                    ?.beginTransaction()
//                    ?.setCustomAnimations(
//                            0, R.anim.slide_out_right,
//                            0, R.anim.slide_out_right)
//                    ?.add(android.R.id.content,
//                            ConversationFragment.newInstance(""),
//                            ConversationFragment.FRAGMENT_TAG)
//                    ?.addToBackStack(ConversationFragment.FRAGMENT_TAG)
//                    ?.commit()
//
//            fragmentManager
//                    ?.beginTransaction()
//                    ?.setCustomAnimations(
//                            0, R.anim.slide_out_right,
//                            0, R.anim.slide_out_right)
//                    ?.add(android.R.id.content,
//                            MembersFragment.newInstance(""),
//                            MembersFragment.FRAGMENT_TAG)
//                    ?.addToBackStack(MembersFragment.FRAGMENT_TAG)
//                    ?.commit()
//
//            fragmentManager
//                    ?.beginTransaction()
//                    ?.setCustomAnimations(
//                            0, R.anim.slide_out_right,
//                            0, R.anim.slide_out_right)
//                    ?.add(android.R.id.content,
//                            MemberFragment.newInstance("", ""),
//                            MemberFragment.FRAGMENT_TAG)
//                    ?.addToBackStack(MemberFragment.FRAGMENT_TAG)
//                    ?.commit()
//        }
//    }

    override fun toOnBoardingWizard() {
        purifyRoute()

        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(android.R.id.content,
                            OnBoardingWizardFragment.newInstance(),
                            OnBoardingWizardFragment.FRAGMENT_TAG)
                    ?.commit()
        }
    }

    override fun toOnBoarding() {
        purifyRoute()

        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(android.R.id.content,
                            OnBoardingFragment.newInstance(),
                            OnBoardingFragment.FRAGMENT_TAG)
                    ?.commit()
        }
    }

    override fun toSignUp() {
        purifyRoute()

        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(android.R.id.content,
                            SignUpFragment.newInstance(),
                            SignUpFragment.FRAGMENT_TAG)
                    ?.commit()
        }
    }

}