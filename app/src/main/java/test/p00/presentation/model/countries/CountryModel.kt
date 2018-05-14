package test.p00.presentation.model.countries

import test.p00.data.model.Country

/**
 * Created by Peter Bukhal on 5/14/18.
 */
data class CountryModel(val id: Int, val name: String, val phoneCode: Int) {

    object Mapper {

        fun map(country: Country) =
                CountryModel(country.id, country.name2, country.phoneCode)

    }

}