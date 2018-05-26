package test.p00.presentation.signup.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotterknife.bindView
import test.p00.R
import test.p00.domain.signup.SignUpInteractor
import test.p00.presentation.activity.abs.AbsFragment
import test.p00.presentation.model.ErrorModel
import test.p00.presentation.model.countries.CountryModel
import test.p00.presentation.signup.SignUpPresenter
import test.p00.presentation.signup.SignUpView
import test.p00.presentation.util.dismissKeyboard
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 5/12/18.
 */
class SignUpFragment : AbsFragment(), SignUpView {

    companion object {

        const val FRAGMENT_TAG = "tag_SignUpFragment"

        fun newInstance(): Fragment = SignUpFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    @Inject lateinit var signUpInteractor: SignUpInteractor

    private val presenter: SignUpPresenter by lazy {
        SignUpPresenterImpl(
                router = SignUpRouterImpl(fragmentManager, this),
                interactor = signUpInteractor)
    }

    override val targetLayout = R.layout.view_sign_up_phone

    private val vCountry: TextView by bindView(R.id.sign_up_country)
    private val vCountryFlag: ImageView by bindView(R.id.sign_up_country_flag)
    private val vCountryCode: EditText by bindView(R.id.sign_up_country_code)
    private val vNumber: EditText by bindView(R.id.sign_up_number)
    private val vVerify: View by bindView(R.id.sign_up_verify)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vCountry.setOnClickListener {
            presenter.changeCountry()

            dismissKeyboard(activity)
        }

        vCountryCode.isFocusable = false
        vCountryCode.isClickable = false

        vVerify.setOnClickListener {
            presenter.confirmPhoneNumber(
                    vCountryCode.text.toString(),
                    vNumber.text.toString())

            dismissKeyboard(activity)
        }

        presenter.attachView(this)
    }

    override fun showSignUpForm(country: CountryModel) {
        vCountry.text = country.name
        vCountryCode.setText(country.callingCode)
        vCountryFlag.apply {
            Glide.with(context)
                 .load(country.flag)
                 .into(vCountryFlag)
        }

        vNumber.requestFocus()
    }

    override fun showLoading() {}

    override fun showError(error: ErrorModel) {
        Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}