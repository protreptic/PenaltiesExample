package test.p00.presentation.signup.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.activity.abs.AbsFragment
import test.p00.presentation.model.countries.CountryModel
import test.p00.presentation.signup.SignUpPresenter
import test.p00.presentation.signup.SignUpView

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

    private val presenter: SignUpPresenter by lazy {
        SignUpPresenterImpl(router = SignUpRouterImpl(fragmentManager, this))
    }

    override val targetLayout = R.layout.view_sign_up

    private val vCountry: EditText by bindView(R.id.sign_up_country)
    private val vCountryFlag: ImageView by bindView(R.id.sign_up_country_flag)
    private val vCountryCode: EditText by bindView(R.id.sign_up_country_code)
    private val vNumber: EditText by bindView(R.id.sign_up_number)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vCountry.setOnClickListener {
            presenter.displayCountries()
        }

        presenter.attachView(this)
    }

    override fun showSignUpForm(country: CountryModel) {
        vCountry.setText(country.name)
        vCountryFlag.apply {
            Glide.with(context)
                 .load(country.flag)
                 .into(vCountryFlag)
        }
        vCountryCode.setText("+${country.phoneCode}")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}