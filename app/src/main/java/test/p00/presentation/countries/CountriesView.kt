package test.p00.presentation.countries

import test.p00.presentation.abs.View
import test.p00.presentation.model.countries.CountryModel

/**
 * Created by Peter Bukhal on 5/14/18.
 */
interface CountriesView : View {

    /**
     *
     */
    fun showCountries(countries: List<CountryModel>)

}