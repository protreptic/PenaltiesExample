package test.p00.presentation.onboarding

import test.p00.presentation.View
import test.p00.presentation.model.onboarding.OnBoardingModel

interface OnBoardingView : View {

    fun displayOnBoarding(onBoarding: OnBoardingModel)

}