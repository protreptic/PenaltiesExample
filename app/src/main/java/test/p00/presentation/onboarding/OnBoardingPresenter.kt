package test.p00.presentation.onboarding

import test.p00.presentation.abs.Presenter

interface OnBoardingPresenter : Presenter<OnBoardingView> {

    fun displayOnBoarding()

}