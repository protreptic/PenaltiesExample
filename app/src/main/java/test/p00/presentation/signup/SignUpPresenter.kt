package test.p00.presentation.signup

import test.p00.presentation.Presenter

/**
 * Created by Peter Bukhal on 5/12/18.
 */
interface SignUpPresenter : Presenter<SignUpView> {

    /**
     *
     */
    fun changeCountry()

    /**
     *
     */
    fun confirmPhoneNumber(countryCode: String, phoneNumber: String)

}