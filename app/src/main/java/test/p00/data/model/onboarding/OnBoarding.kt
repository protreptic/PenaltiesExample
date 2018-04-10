package test.p00.data.model.onboarding

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class OnBoarding : RealmObject() {

    companion object {

        const val FIELD_ID = "id"
        const val FIELD_NUMBER_OF_IMPRESSIONS = "numberOfImpressions"
        const val FIELD_BACKGROUND_URI = "backgroundUri"
        const val FIELD_PAGES = "pages"

    }

    @PrimaryKey
    var id: Int = 0
    var numberOfImpressions: Int = 0
    var backgroundUri: String = ""
    var pages: RealmList<OnBoardingPage> = RealmList()

}