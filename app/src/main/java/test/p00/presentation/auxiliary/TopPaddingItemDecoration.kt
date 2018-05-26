package test.p00.presentation.auxiliary

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Peter Bukhal on 5/15/18.
 */
class TopPaddingItemDecoration(private val paddingTop: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = (paddingTop * view.context.resources.displayMetrics.density).toInt()
        }
    }

}