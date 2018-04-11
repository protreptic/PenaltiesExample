package test.p00.data.repository.onboarding.datasource.impl

import io.reactivex.Observable
import io.reactivex.Observable.*
import io.realm.Realm
import test.p00.R
import test.p00.data.model.onboarding.OnBoarding
import test.p00.data.model.onboarding.OnBoardingPage
import test.p00.data.repository.onboarding.datasource.OnBoardingDataSource
import test.p00.util.ContextProvider

class OnBoardingRealmDataSource : OnBoardingDataSource {

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun fetch(): Observable<OnBoarding> {
        Realm.getDefaultInstance().use { storage ->
            val onBoarding =
                    storage.where(OnBoarding::class.java)
                           .findFirst()

            /*
             * Создаем новый приветственный экран,
             * если это первый запуск приложения.
             */
            return when (onBoarding == null) {
                true -> retain(generateOnBoarding())
                else -> just(storage.copyFromRealm(onBoarding))
            }
        }
    }

    override fun retain(onBoarding: OnBoarding): Observable<OnBoarding> {
        Realm.getDefaultInstance().use { storage ->
            storage.beginTransaction()

            val retainedOnBoarding =
                    storage.copyFromRealm(
                    storage.copyToRealmOrUpdate(onBoarding))

            storage.commitTransaction()

            return just(retainedOnBoarding)
        }
    }

    companion object {

        const val BASE_URI = "file:///android_asset/"
    }

    private fun generateOnBoarding() = OnBoarding().apply {
        numberOfImpressions = 0
        backgroundUri = "onboarding/wt_background.png"

        pages.apply {
            add(OnBoardingPage().apply {
                order = 1
                message = ContextProvider.context().getString(R.string.onboarding_message_1)
                contentUri = BASE_URI + "onboarding/wt_1.png"
            })
            add(OnBoardingPage().apply {
                order = 2
                message = ContextProvider.context().getString(R.string.onboarding_message_2)
                contentUri = BASE_URI + "onboarding/wt_2.png"
            })
            add(OnBoardingPage().apply {
                order = 3
                message = ContextProvider.context().getString(R.string.onboarding_message_3)
                contentUri = BASE_URI + "onboarding/wt_3.png"
            })
            add(OnBoardingPage().apply {
                order = 4
                message = ContextProvider.context().getString(R.string.onboarding_message_4)
                contentUri = BASE_URI + "onboarding/wt_4.png"
            })
            add(OnBoardingPage().apply {
                order = 5
                message = ContextProvider.context().getString(R.string.onboarding_message_5)
                contentUri = BASE_URI + "onboarding/wt_5.png"
            })
            add(OnBoardingPage().apply {
                order = 6
                message = ContextProvider.context().getString(R.string.onboarding_message_6)
            })
        }
    }

}