package test.p00.presentation.auxiliary

import android.support.v7.widget.RecyclerView
import android.view.View

class RecyclerViewElevationDelegate(
        private val recyclerView: RecyclerView,
        override var elevation: Int):
            ElevationDelegate {

    private val listener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            when (recyclerView.canScrollVertically(-1)) {
                true -> applyElevation(elevation)
                else -> applyElevation(0)
            }
        }
    }

    override lateinit var view: View

    override fun attachTo(view: View) {
        this.view = view
        this.recyclerView.addOnScrollListener(listener)
    }

}