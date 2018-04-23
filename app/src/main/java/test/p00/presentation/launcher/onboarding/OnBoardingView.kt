package test.p00.presentation.launcher.onboarding

import test.p00.presentation.abs.View
import test.p00.presentation.launcher.onboarding.model.OnBoardingModel

interface OnBoardingView : View {

    fun displayOnBoarding(onBoarding: OnBoardingModel)

}