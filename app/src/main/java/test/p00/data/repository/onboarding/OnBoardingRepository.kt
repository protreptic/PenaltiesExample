package test.p00.data.repository.onboarding

import io.reactivex.Observable
import test.p00.data.model.onboarding.OnBoarding

interface OnBoardingRepository {

    /**
     *
     */
    fun fetch(): Observable<OnBoarding>

    /**
     *
     */
    fun retain(onBoarding: OnBoarding): Observable<OnBoarding>

}