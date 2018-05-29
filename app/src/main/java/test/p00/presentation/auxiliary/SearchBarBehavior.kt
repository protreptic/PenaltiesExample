package test.p00.presentation.auxiliary

import android.animation.ObjectAnimator
import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator

/**
 * Created by Peter Bukhal on 5/29/18.
 */
@Suppress("unused")
class SearchBarBehavior : CoordinatorLayout.Behavior<View> {

    constructor(): super()
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: View,
                                     directTargetChild: View, target: View,
                                     axes: Int, type: Int): Boolean {
        return true
    }

    private var slideOutAnimator: ObjectAnimator? = null
    private var slideInAnimator: ObjectAnimator? = null
    
    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: View,
                                target: View, dxConsumed: Int, dyConsumed: Int,
                                dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        if (dyConsumed > 0) {
            stopAnimation(slideInAnimator)

            if (animationNotStarted(slideOutAnimator)) {
                slideOutAnimator = animationTranslationY(child, translationY(child)).apply {
                    start()
                }
            }
        } else if (dyConsumed < 0) {
            stopAnimation(slideOutAnimator)

            if (animationNotStarted(slideInAnimator)) {
                slideInAnimator = animationTranslationY(child, 0.0F).apply {
                    start()
                }
            }
        }
    }

    private fun stopAnimation(animator: ObjectAnimator?) {
        if (animator != null && animator.isStarted) {
            animator.cancel()
        }
    }

    private fun animationNotStarted(animator: ObjectAnimator?) =
            animator == null || !animator.isStarted

    private fun animationTranslationY(view: View, translationY: Float): ObjectAnimator =
            ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, view.translationY, translationY).apply {
                duration = 200L
                interpolator = DecelerateInterpolator()
            }

    private fun translationY(child: View) =
            -(child.top.toFloat() +
                    child.measuredHeight.toFloat() +
                            ViewCompat.getElevation(child))

}