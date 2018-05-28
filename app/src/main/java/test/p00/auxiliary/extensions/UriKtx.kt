package test.p00.auxiliary.extensions

import android.net.Uri

/**
 * Created by Peter Bukhal on 5/28/18.
 */
fun Uri.deepLink(): Uri = Uri.parse("$scheme://$host$path")