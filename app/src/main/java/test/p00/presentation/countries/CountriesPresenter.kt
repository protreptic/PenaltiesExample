package test.p00.presentation.countries

import test.p00.presentation.Presenter
import test.p00.presentation.model.countries.CountryModel

/**
 * Created by Peter Bukhal on 5/14/18.
 */
interface CountriesPresenter : Presenter<CountriesView> {

    /**
     * Событие наступает когда пользователь
     * выбирает страну из списка стран.
     *
     * @param pickedCountry выбранная страна
     */
    data class CountryPickedEvent(val pickedCountry: CountryModel)

    /**
     *
     */
    fun displayCountries()

    /**
     *
     */
    fun displayCountries(name: String)

    /**
     *
     */
    fun pickCountry(country: CountryModel)

}