package test.p00.presentation.signup

import test.p00.presentation.abs.View
import test.p00.presentation.model.ErrorModel
import test.p00.presentation.model.countries.CountryModel

/**
 * Created by Peter Bukhal on 5/12/18.
 */
interface SignUpView : View {

    /**
     *
     */
    fun showSignUpForm(country: CountryModel)

    /**
     *
     */
    fun showLoading()

    /**
     *
     */
    fun showError(error: ErrorModel)

}