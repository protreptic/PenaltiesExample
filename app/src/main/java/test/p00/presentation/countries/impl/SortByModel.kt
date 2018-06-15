package test.p00.presentation.countries.impl

import test.p00.presentation.countries.CountriesView

/**
 * Created by Peter Bukhal on 6/9/18.
 */
data class SortByModel(
        val name: String = "",
        val sortBy: CountriesView.SortBy,
        val default: Boolean = false) {

    override fun toString() = name

}