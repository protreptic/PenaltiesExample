package test.p00.widget.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import test.p00.R

/**
 * Created by Peter Bukhal on 5/15/18.
 */
class SearchBehavior : CoordinatorLayout.Behavior<CardView> {

    constructor()
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun layoutDependsOn(parent: CoordinatorLayout, child: CardView, dependency: View) =
        when (child.id) {
            R.id.vCountries -> true
            else -> false
        }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: CardView, dependency: View): Boolean {
        Log.d("Behavior", "onDependentViewChanged: $dependency")

        return super.onDependentViewChanged(parent, child, dependency)
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: CardView, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
    }

}