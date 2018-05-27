package test.p00.presentation.countries

import test.p00.presentation.Presenter
import test.p00.presentation.model.countries.CountryModel

/**
 * Created by Peter Bukhal on 5/14/18.
 */
interface CountriesPresenter : Presenter<CountriesView> {

    /**
     * Отображает страны в названии которых содержит
     * переданный шаблон.
     *
     * @param name шаблон названия страны
     */
    fun displayCountries(name: String = "")

    /**
     * Пользователь выбрал страну.
     *
     * @param country выбранная пользователем страна
     */
    fun pickCountry(country: CountryModel)

}