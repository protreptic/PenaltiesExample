package test.p00.presentation.activity.deeplinking.impl

import android.app.Activity
import android.net.Uri
import android.net.Uri.*
import test.p00.presentation.activity.deeplinking.DeepLink
import test.p00.presentation.activity.deeplinking.DeepLinkSupervisor
import test.p00.presentation.activity.deeplinking.DeepLinkSupervisor.Delegate


/**
 * Created by Peter Bukhal on 4/29/18.
 */
class DeepLinkSupervisorImpl(
        private val activity: Activity,
        private val delegate: Delegate) : DeepLinkSupervisor {

    companion object {

        const val DEEPLINK_HOST = "https://fruit-chat.herokuapp.com"

        val DEEPLINK_HOME = parse("$DEEPLINK_HOST/home")
        val DEEPLINK_CONVERSATIONS = parse("$DEEPLINK_HOST/home/conversations")

        val deeplinks = mutableMapOf<Uri, DeepLink>().apply {
            put(DEEPLINK_HOME, DeepLinkImpl(DeepLinkRouteImpl()))
            put(DEEPLINK_CONVERSATIONS, DeepLinkImpl(DeepLinkRouteImpl()))
        }

    }

    override fun open(uri: Uri) {
        if (deeplinks.containsKey(uri)) {
            deeplinks[uri]?.open()
        } else {
            delegate.onDeepLinkNotFound(uri)
        }
    }

}