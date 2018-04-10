package test.p00.data.repository.onboarding.datasource

import io.reactivex.Observable
import test.p00.data.model.onboarding.OnBoarding

interface OnBoardingDataSource {

    /**
     * Извлекает из истоника приветственный экран.
     */
    fun fetch(): Observable<OnBoarding>

    /**
     * Сохраняет в источнике данных приветственный экран.
     *
     * @param onBoarding приветственный экран
     */
    fun retain(onBoarding: OnBoarding): Observable<OnBoarding>

}