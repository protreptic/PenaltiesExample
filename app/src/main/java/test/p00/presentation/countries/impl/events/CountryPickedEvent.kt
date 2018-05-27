package test.p00.presentation.countries.impl.events

import test.p00.auxiliary.bus.BusEvent
import test.p00.presentation.model.countries.CountryModel

/**
 * Событие наступает когда пользователь
 * выбирает страну из списка стран.
 *
 * @param pickedCountry выбранная страна
 */
data class CountryPickedEvent(val pickedCountry: CountryModel): BusEvent