package test.p00.presentation.auxiliary

import android.view.View

/**
 * Created by Peter Bukhal on 5/29/18.
 */
abstract class DebouncingOnClickListener : View.OnClickListener {

    companion object {

        private var enable = true
        private val enableAgain = Runnable { enable = true }

    }

    override fun onClick(view: View) {
        if (enable) {
            enable = false

            view.post(enableAgain)

            onDebouncingClick(view)
        }
    }

    abstract fun onDebouncingClick(view: View)

}