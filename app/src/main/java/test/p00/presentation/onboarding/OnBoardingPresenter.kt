package test.p00.presentation.onboarding

import test.p00.presentation.Presenter

interface OnBoardingPresenter : Presenter<OnBoardingView> {

    fun displayOnBoarding()
    fun closeOnBoarding()

}