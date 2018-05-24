package test.p00.util.extension

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import test.p00.R
import java.io.Serializable

fun Fragment.subscribe(fragment: Fragment): Fragment {
    setTargetFragment(fragment, 0)

    return this
}

fun Fragment.notify(result: Serializable) {
    targetFragment?.onActivityResult(0, 0,
            Intent().putExtra("123", result))
}