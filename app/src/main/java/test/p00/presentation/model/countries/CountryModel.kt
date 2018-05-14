package test.p00.presentation.model.countries

import test.p00.data.model.countries.Country

/**
 * Created by Peter Bukhal on 5/14/18.
 */
data class CountryModel(val id: Int, val iso: String, val name: String, val phoneCode: Int) {

    object Mapper {

        fun map(country: Country) =
                CountryModel(country.id, country.iso, country.name2, country.phoneCode)

    }

}