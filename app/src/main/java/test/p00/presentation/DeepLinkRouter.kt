package test.p00.presentation

import android.net.Uri

interface DeepLinkRouter : Router {

    fun openDeepLink(deepLink: Uri)

}