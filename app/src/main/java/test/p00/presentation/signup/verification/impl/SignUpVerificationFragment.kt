package test.p00.presentation.signup.verification.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.EditText
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.activity.abs.AbsFragment
import test.p00.presentation.signup.impl.SignUpRouterImpl
import test.p00.presentation.signup.verification.SignUpVerificationPresenter
import test.p00.presentation.signup.verification.SignUpVerificationView

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class SignUpVerificationFragment : AbsFragment(), SignUpVerificationView {

    companion object {

        const val FRAGMENT_TAG = "tag_SignUpVerificationFragment"

        fun newInstance(): Fragment = SignUpVerificationFragment().apply {
            arguments = Bundle.EMPTY
        }


    }

    private val presenter: SignUpVerificationPresenter by lazy {
        SignUpVerificationPresenterImpl(router = SignUpRouterImpl(fragmentManager, this))
    }

    override val targetLayout = R.layout.view_sign_up_verification

    private val vVerificationCode: EditText by bindView(R.id.sign_up_verification_code)
    private val vConfirm: View by bindView(R.id.sign_up_confirm)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vVerificationCode.apply {

        }
        vConfirm.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}