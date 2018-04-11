package test.p00.data.model.onboarding

import io.realm.RealmObject

open class OnBoardingPage : RealmObject() {

    companion object {

        const val FIELD_ORDER = "order"
        const val FIELD_MESSAGE = "message"
        const val FIELD_CONTENT_URI = "contentUri"

    }

    var order: Int = 0
    var message: String = ""
    var contentUri: String = ""

}