package test.p00.data.model.conversation.message.content

import io.realm.RealmObject

/**
 * Created by Peter Bukhal on 4/28/18.
 */
open class ContentLocation : RealmObject() {

    companion object {

        const val FIELD_LATITUDE = "latitude"
        const val FIELD_LONGITUDE = "longitude"

        val DEFAULT = ContentLocation()

    }

    var latitude: Float = 0.0F
    var longitude: Float = 0.0F

}