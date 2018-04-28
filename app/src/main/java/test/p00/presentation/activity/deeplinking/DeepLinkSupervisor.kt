package test.p00.presentation.activity.deeplinking

import android.net.Uri


/**
 * Created by Peter Bukhal on 4/29/18.
 */
interface DeepLinkSupervisor {

    interface Delegate {

        fun onDeepLinkNotFound(uri: Uri)

    }

    /**
     *
     */
    fun open(uri: Uri)

}