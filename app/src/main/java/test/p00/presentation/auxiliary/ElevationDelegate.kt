package test.p00.presentation.auxiliary

import android.content.Context
import android.support.v4.view.ViewCompat
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.View

interface ElevationDelegate {

    companion object {

        fun create(recyclerView: RecyclerView, elevation: Int): ElevationDelegate =
                RecyclerViewElevationDelegate(recyclerView, elevation)

        fun create(nestedScrollView: NestedScrollView, elevation: Int): ElevationDelegate =
                NestedScrollViewElevationDelegate(nestedScrollView, elevation)

    }

    var view: View
    var elevation: Int

    /**
     * Прикрепляет делегат к
     * указанному представлению.
     *
     * @param view предсталение
     */
    fun attachTo(view: View)

    /**
     * Устанавливает высоту представления
     * на заданную величину.
     *
     * @param elevation высота представления
     */
    fun applyElevation(elevation: Int) {
        ViewCompat.setElevation(view, dp(view.context, elevation).toFloat())
    }

    private fun dp(context: Context, px: Int): Int =
            px * (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)

}