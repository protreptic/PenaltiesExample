package test.p00.presentation.onboarding.wizard.introductory.impl

import android.graphics.drawable.Drawable.createFromStream
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.impl.abs.AbsView
import test.p00.presentation.onboarding.wizard.impl.OnBoardingWizardRouterImpl
import test.p00.presentation.onboarding.wizard.introductory.OnBoardingWizardIntroductoryPresenter
import test.p00.presentation.onboarding.wizard.introductory.OnBoardingWizardIntroductoryView
import test.p00.presentation.auxiliary.glide.GlideApp

class OnBoardingWizardIntroductoryFragment : AbsView(), OnBoardingWizardIntroductoryView {

    companion object {

        const val FRAGMENT_TAG = "tag_OnBoardingWizardIntroductoryFragment"

        fun newInstance(): Fragment = OnBoardingWizardIntroductoryFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    private val presenter: OnBoardingWizardIntroductoryPresenter by lazy {
        OnBoardingWizardIntroductoryPresenterImpl(OnBoardingWizardRouterImpl(fragmentManager, this))
    }

    override val targetLayout: Int = R.layout.view_onboarding_wizard_introductory

    private val vContentBackground: View by bindView(R.id.vContentBackground)
    private val vContentText: TextView by bindView(R.id.vContentText)
    private val vContentPicture: ImageView by bindView(R.id.vContentPicture)
    private val vGo: View by bindView(R.id.vGo)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vContentBackground.background =
                createFromStream(context!!.assets.open("onboarding/wt_background.png"), null)
        vContentText.text = getString(R.string.onboarding_wizard_introductory_message)

        GlideApp.with(context!!)
                .load("file:///android_asset/onboarding/wizard/wt_introductory.png")
                .centerInside()
                .into(vContentPicture)

        vGo.setOnClickListener { presenter.displayBeginning() }

        presenter.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}