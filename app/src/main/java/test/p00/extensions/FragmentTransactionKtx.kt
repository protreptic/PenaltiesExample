package test.p00.extensions

import android.support.v4.app.FragmentTransaction
import test.p00.R

/**
 * Created by Peter Bukhal on 5/24/18.
 */
fun FragmentTransaction.defaultAnimation(): FragmentTransaction {
    setCustomAnimations(
            R.anim.slide_in_right, R.anim.slide_out_right,
            R.anim.slide_in_right, R.anim.slide_out_right)

    return this
}