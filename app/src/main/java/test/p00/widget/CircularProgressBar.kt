package test.p00.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator

/**
 * Created by Peter Bukhal on 5/15/18.
 */
class CircularProgressBar : View {

    private var mViewWidth: Int = 0
    private var mViewHeight: Int = 0

    private val mStartAngle = -90f      // Always start from top (default is: "3 o'clock on a watch.")
    private var mSweepAngle = 0f              // How long to sweep from mStartAngle
    private val mMaxSweepAngle = 360f         // Max degrees to sweep = full circle
    private var mStrokeWidth = 30              // Width of outline
    private var mAnimationDuration = 100L       // Animation duration for progress change
    private var mMaxProgress = 100             // Max progress to use
    private var mDrawText = false           // Set to true if progress text should be drawn
    private var mRoundedCorners = false     // Set to true if rounded corners should be applied to outline ends
    private var mProgressColor = Color.BLACK   // Outline color
    private var mTextColor = Color.BLACK       // Progress text color

    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG) // Allocate paint outside onDraw to avoid unnecessary object creation

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        initMeasurements()
        drawOutlineArc(canvas)

        if (mDrawText) {
            drawText(canvas)
        }
    }

    private fun initMeasurements() {
        mViewWidth = width
        mViewHeight = height
    }

    private fun drawOutlineArc(canvas: Canvas) {
        val diameter = Math.min(mViewWidth, mViewHeight)
        val pad = (mStrokeWidth / 2.0F)
        val outerOval = RectF(pad, pad, diameter - pad, diameter - pad)

        mPaint.apply {
            color = mProgressColor
            strokeWidth = mStrokeWidth.toFloat()
            isAntiAlias = true
            strokeCap = if (mRoundedCorners) Paint.Cap.ROUND else Paint.Cap.BUTT
            style = Paint.Style.STROKE
        }

        canvas.drawArc(outerOval, mStartAngle, mSweepAngle, false, mPaint)
    }

    private fun drawText(canvas: Canvas) {
        mPaint.apply {
            textSize = Math.min(mViewWidth, mViewHeight) / 5.0F
            textAlign = Paint.Align.CENTER
            strokeWidth = 0.0F
            color = mTextColor
        }

        // Center text
        val xPos = (canvas.width / 2.0F)
        val yPos = (canvas.height / 2.0F - (mPaint.descent() + mPaint.ascent()) / 2.0F)

        canvas.drawText(calcProgressFromSweepAngle(mSweepAngle).toString(), xPos, yPos, mPaint)
    }

    private fun calcSweepAngleFromProgress(progress: Int): Float {
        return mMaxSweepAngle / mMaxProgress * progress
    }

    private fun calcProgressFromSweepAngle(sweepAngle: Float): Int {
        return (sweepAngle * mMaxProgress / mMaxSweepAngle).toInt()
    }

    /**
     * Set progress of the circular progress bar.
     * @param progress progress between 0 and 100.
     */
    fun setProgress(progress: Int) {
        ValueAnimator.ofFloat(mSweepAngle, calcSweepAngleFromProgress(progress)).apply {
            interpolator = DecelerateInterpolator()
            duration = mAnimationDuration

            addUpdateListener { valueAnimator ->
                mSweepAngle = valueAnimator.animatedValue as Float

                invalidate()
            }
        }.start()
    }

    fun setMaxProgress(maxProgress: Int) {
        mMaxProgress = maxProgress
    }

    fun setAnimationDuration(duration: Long) {
        mAnimationDuration = duration
    }

    fun setProgressColor(color: Int) {
        mProgressColor = color

        invalidate()
    }

    fun setProgressWidth(width: Int) {
        mStrokeWidth = width

        invalidate()
    }

    fun setTextColor(color: Int) {
        mTextColor = color

        invalidate()
    }

    fun showProgressText(show: Boolean) {
        mDrawText = show

        invalidate()
    }

    /**
     * Toggle this if you don't want rounded corners on progress bar.
     * Default is true.
     * @param roundedCorners true if you want rounded corners of false otherwise.
     */
    fun useRoundedCorners(roundedCorners: Boolean) {
        mRoundedCorners = roundedCorners

        invalidate()
    }

}