package test.p00.data.repository.onboarding.datasource.impl

import io.reactivex.Observable
import io.reactivex.Observable.*
import io.realm.Realm
import test.p00.data.model.onboarding.OnBoarding
import test.p00.data.model.onboarding.OnBoardingPage
import test.p00.data.repository.onboarding.datasource.OnBoardingDataSource

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

    private fun generateOnBoarding() = OnBoarding().apply {
        numberOfImpressions = 0
        backgroundUri = "file:///android_asset/1326661278_doroga.jpg"

        pages.apply {
            add(OnBoardingPage().apply {
                order = 1
                contentUri = "file:///android_asset/1326661278_doroga.jpg"
            })
            add(OnBoardingPage().apply {
                order = 2
                contentUri = "http://www.arnapress.kz/i/Posts/98632.jpg"
            })
            add(OnBoardingPage().apply {
                order = 4
                contentUri = "http://effloresce.ru/wp-content/uploads/2016/06/%D1%82%D1%80%D0%B8-%D0%B4%D0%BE%D1%80%D0%BE%D0%B3%D0%B8-%D1%82%D1%80%D0%B8-%D0%BF%D1%83%D1%82%D0%B83.jpg"
            })
            add(OnBoardingPage().apply {
                order = 7
                contentUri = "https://i.pinimg.com/originals/7f/b7/c8/7fb7c8a87ceb75d9f73de3d79d447d38.jpg"
            })
            add(OnBoardingPage().apply {
                order = 3
                contentUri = "https://dorig.net.ua/media/9/e/c/194/9ec2619aceb17f4546e5845bcf624c72.jpg"
            })
            add(OnBoardingPage().apply {
                order = 6
                contentUri = "https://www.computerworld.ru/FileStorage/DOCUMENTS_ILLUSTRATIONS/13209466/i_800.jpg"
            })
            add(OnBoardingPage().apply {
                order = 5
                contentUri = "http://img-fotki.yandex.ru/get/9930/106089749.21/0_e5530_e083285e_orig.jpg"
            })
        }
    }

}