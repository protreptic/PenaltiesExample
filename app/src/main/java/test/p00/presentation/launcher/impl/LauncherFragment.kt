package test.p00.presentation.launcher.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import test.p00.R
import test.p00.presentation.activity.abs.AbsFragment
import test.p00.presentation.launcher.LauncherPresenter
import test.p00.presentation.launcher.LauncherView

class LauncherFragment : AbsFragment(), LauncherView {

    companion object {

        const val FRAGMENT_TAG = "tag_LauncherFragment"

        fun newInstance(): Fragment = LauncherFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    private val presenter: LauncherPresenter by lazy {
        LauncherPresenterImpl(router = LauncherRouterImpl(fragmentManager, this))
    }

    override val targetLayout: Int = R.layout.view_launcher

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}