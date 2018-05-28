package test.p00.auxiliary.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE

fun FragmentManager.push(
        fragment: Fragment, fragmentTag: String = fragment::class.java.simpleName,
                containerId: Int = android.R.id.content, animate: Boolean = true,
                        addToBackStack: Boolean = true) {
    beginTransaction().apply {
        if (animate) {
            defaultAnimation()
        }

        add(containerId, fragment, fragmentTag)

        if (addToBackStack) {
            addToBackStack(fragmentTag)
        }

        commit()
    }
}

fun FragmentManager.pushRoot(
        fragment: Fragment, fragmentTag: String = fragment::class.java.simpleName,
                containerId: Int = android.R.id.content) {
    beginTransaction()
        .replace(containerId, fragment, fragmentTag)
        .commit()
}

fun FragmentManager.pop(tag: String? = null, immediate: Boolean = false) {
    if (immediate) {
        popBackStackImmediate()
    } else {
        popBackStack(tag, POP_BACK_STACK_INCLUSIVE)
    }
}