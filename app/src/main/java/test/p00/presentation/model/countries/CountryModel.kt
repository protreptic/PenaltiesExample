package test.p00.presentation.model.countries

import test.p00.data.model.countries.Country
import java.io.Serializable

/**
 * Created by Peter Bukhal on 5/14/18.
 */
data class CountryModel(
        val code: Int,
        val iso2: String,
        val iso3: String,
        val name: String,
        val flag: String,
        val callingCode: String):
        Serializable {

    object Mapper {

        fun map(country: Country) =
                CountryModel(
                        country.code,
                        country.iso2,
                        country.iso3,
                        country.name,
                        country.flag,
                        String.format("+%d", country.callingCode))

    }

}