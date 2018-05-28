package test.p00.auxiliary.extensions

import android.net.Uri

fun String.uri(): Uri = Uri.parse(this)