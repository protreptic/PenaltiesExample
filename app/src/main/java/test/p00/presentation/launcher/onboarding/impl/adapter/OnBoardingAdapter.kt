package test.p00.presentation.launcher.onboarding.impl.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import test.p00.R
import test.p00.presentation.launcher.onboarding.model.OnBoardingModel
import test.p00.util.glide.GlideApp

class OnBoardingAdapter(private val context: Context,
                        private val onBoarding: OnBoardingModel): PagerAdapter() {

    override fun isViewFromObject(view1: View, view2: Any) = (view1 == view2)

    override fun getCount() = onBoarding.pages.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.view_onboarding_page, container, false)
        val model = onBoarding.pages[position]

        view.findViewById<TextView>(R.id.vContentText)?.apply {
            text = model.message
        }

        view.findViewById<ImageView>(R.id.vContentPicture)?.apply {
            when (model.contentUri.isNotEmpty()) {
                true -> {
                    GlideApp.with(context)
                            .load(model.contentUri)
                            .centerInside()
                            .into(this)
                }
                else -> {
                    visibility = View.GONE
                }
            }
        }

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

}