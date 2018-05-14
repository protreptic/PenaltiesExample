package test.p00.presentation.countries

import test.p00.presentation.abs.Presenter

/**
 * Created by Peter Bukhal on 5/14/18.
 */
interface CountriesPresenter : Presenter<CountriesView> {

    /**
     *
     */
    fun displayCountries()

}