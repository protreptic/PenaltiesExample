package test.p00.auxiliary.extensions

import android.view.View
import test.p00.presentation.auxiliary.DebouncingOnClickListener

/**
 * Created by Peter Bukhal on 5/29/18.
 */
fun View.setDebouncingOnClickListener(block: (view: View) -> Unit) {
    setOnClickListener(object : DebouncingOnClickListener() {
        override fun onDebouncingClick(view: View) {
            block(view)
        }
    })
}