package test.p00.presentation.auxiliary

import android.support.v4.widget.NestedScrollView
import android.view.View

class NestedScrollViewElevationDelegate(
        private val nestedScrollView: NestedScrollView,
        override var elevation: Int):
            ElevationDelegate, NestedScrollView.OnScrollChangeListener {

    override fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int,
                                        oldScrollX: Int, oldScrollY: Int) {
        when (scrollY) {
            0 -> applyElevation(0)
            else -> applyElevation(elevation)
        }
    }

    override lateinit var view: View

    override fun attachTo(view: View) {
        this.view = view
        this.nestedScrollView.setOnScrollChangeListener(this)
    }

}