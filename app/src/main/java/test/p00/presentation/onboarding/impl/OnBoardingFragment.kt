package test.p00.presentation.onboarding.impl

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.viewpagerindicator.CirclePageIndicator
import kotterknife.bindView
import test.p00.R
import test.p00.activity.abs.AbsFragment
import test.p00.presentation.onboarding.OnBoardingPresenter
import test.p00.presentation.onboarding.OnBoardingView
import test.p00.presentation.onboarding.impl.adapter.OnBoardingAdapter
import test.p00.presentation.onboarding.model.OnBoardingModel

class OnBoardingFragment : AbsFragment(), OnBoardingView {

    companion object {

        const val FRAGMENT_TAG = "tag_OnBoardingFragment"

        fun newInstance(): Fragment = OnBoardingFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    private val presenter: OnBoardingPresenter by lazy {
        OnBoardingPresenterImpl(router = OnBoardingRouterImpl(fragmentManager))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.view_onboarding, container, false)

    private val vOnBoarding: ViewPager by bindView(R.id.vOnBoarding)
    private val vIndicator: CirclePageIndicator by bindView(R.id.vIndicator)
    private val vForward: View by bindView(R.id.vForward)
    private val vGo: View by bindView(R.id.vGo)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
    }

    override fun displayOnBoarding(onBoarding: OnBoardingModel) {
        vOnBoarding.apply {
            adapter = OnBoardingAdapter(context, onBoarding)
            offscreenPageLimit = onBoarding.pages.size / 2
            background = Drawable.createFromStream(
                    context.assets.open(onBoarding.backgroundUri), null)

            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

                override fun onPageSelected(position: Int) {
                    if (checkIfPositionLastPage(position)) {
                        vGo.visibility = View.VISIBLE
                        vForward.visibility = View.GONE
                        vIndicator.visibility = View.GONE
                    } else {
                        vGo.visibility = View.GONE
                        vForward.visibility = View.VISIBLE
                        vIndicator.visibility = View.VISIBLE
                    }
                }
            })
        }

        vIndicator.setViewPager(vOnBoarding)
        vForward.setOnClickListener {
            vOnBoarding.setCurrentItem(vOnBoarding.currentItem + 1, true)
        }
        vGo.setOnClickListener { if (checkIfLastPage()) { presenter.closeOnBoarding() } }
    }

    private fun checkIfPositionLastPage(position: Int) =
            position == vOnBoarding.adapter?.count!!.minus(1)

    private fun checkIfLastPage() =
            vOnBoarding.currentItem == vOnBoarding.adapter?.count!!.minus(1)

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}