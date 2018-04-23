package test.p00.presentation.launcher.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import test.p00.R
import test.p00.activity.abs.AbsFragment
import test.p00.presentation.launcher.LauncherPresenter
import test.p00.presentation.launcher.LauncherView
import test.p00.presentation.onboarding.OnBoardingFragment
import test.p00.presentation.onboarding.wizard.OnBoardingWizardFragment
import test.p00.presentation.penalty.PenaltiesFragment

class LauncherFragment : AbsFragment(), LauncherView {

    companion object {

        const val FRAGMENT_TAG = "tag_LauncherFragment"

        fun newInstance(): Fragment = LauncherFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    private val presenter: LauncherPresenter by lazy {
        LauncherPresenterImpl()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.view_launcher, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
    }

    override fun runMain() {
        fragmentManager!!
                .beginTransaction()
                .replace(android.R.id.content,
                        PenaltiesFragment.newInstance(),
                        PenaltiesFragment.FRAGMENT_TAG)
                .commit() }

    override fun runOnBoardingWizard() {
        fragmentManager!!
                .beginTransaction()
                .replace(android.R.id.content,
                        OnBoardingWizardFragment.newInstance(),
                        OnBoardingWizardFragment.FRAGMENT_TAG)
                .commit() }

    override fun runOnBoarding() {
        fragmentManager!!
                .beginTransaction()
                .replace(android.R.id.content,
                        OnBoardingFragment.newInstance(),
                        OnBoardingFragment.FRAGMENT_TAG)
                .commit() }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}