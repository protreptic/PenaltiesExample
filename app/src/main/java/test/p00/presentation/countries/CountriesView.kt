package test.p00.presentation.countries

import test.p00.presentation.View
import test.p00.presentation.countries.impl.SortByModel
import test.p00.presentation.model.countries.CountryModel

/**
 * Created by Peter Bukhal on 5/14/18.
 */
interface CountriesView : View {

    enum class SortBy { NAME, PHONE }

    /**
     *
     */
    fun showCountries(countries: List<CountryModel>, sortBy: List<SortByModel>)

}