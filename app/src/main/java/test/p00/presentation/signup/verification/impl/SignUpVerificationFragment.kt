package test.p00.presentation.signup.verification.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.EditText
import kotterknife.bindView
import test.p00.R
import test.p00.domain.signup.SignUpInteractor
import test.p00.presentation.impl.abs.AbsView
import test.p00.presentation.auxiliary.dismissKeyboard
import test.p00.presentation.signup.impl.SignUpRouterImpl
import test.p00.presentation.signup.verification.SignUpVerificationPresenter
import test.p00.presentation.signup.verification.SignUpVerificationView
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class SignUpVerificationFragment : AbsView(), SignUpVerificationView {

    companion object {

        const val FRAGMENT_TAG = "tag_SignUpVerificationFragment"

        fun newInstance(): Fragment = SignUpVerificationFragment().apply {
            arguments = Bundle.EMPTY
        }


    }

    @Inject lateinit var signUpInteractor: SignUpInteractor

    private val presenter: SignUpVerificationPresenter by lazy {
        SignUpVerificationPresenterImpl(
                schedulers = schedulers,
                router = SignUpRouterImpl(fragmentManager, this),
                interactor = signUpInteractor)
    }

    override val targetLayout = R.layout.view_sign_up_phone_verification

    private val vVerificationCode: EditText by bindView(R.id.sign_up_verification_code)
    private val vVerificationCodeConfirm: View by bindView(R.id.sign_up_verification_code_confirm)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vVerificationCode.apply {
            requestFocus()
        }

        vVerificationCodeConfirm.setOnClickListener {
            presenter.verifyCode(vVerificationCode.text.toString())

            dismissKeyboard(activity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}