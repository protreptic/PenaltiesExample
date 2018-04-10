package test.p00.data.model.settings

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Settings : RealmObject() {

    companion object {

        const val WAS_ON_BOARDING_SHOWN = "wasOnBoardingShown"
        const val WAS_ON_BOARDING_WIZARD_SHOWN = "wasOnBoardingWizardShown"

    }

    @PrimaryKey
    var id: Int = 0
    var wasOnBoardingShown: Boolean = false
    var wasOnBoardingWizardShown: Boolean = false

}