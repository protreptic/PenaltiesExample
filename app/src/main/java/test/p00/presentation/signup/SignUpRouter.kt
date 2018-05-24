package test.p00.presentation.signup

import test.p00.presentation.abs.Router

/**
 * Created by Peter Bukhal on 5/12/18.
 */
interface SignUpRouter : Router {

    /**
     *
     */
    fun toPhone()

    /**
     *
     */
    fun toVerification()

    /**
     *
     */
    fun toLauncher()

    /**
     *
     */
    fun toCountries()

}