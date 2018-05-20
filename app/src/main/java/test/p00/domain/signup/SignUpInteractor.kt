package test.p00.domain.signup

import io.reactivex.Completable
import io.reactivex.Observable
import test.p00.data.model.countries.Country
import test.p00.data.remote.authorize.Authorize
import test.p00.data.remote.authorize.request.AuthorizeConfirmationRequest
import test.p00.data.remote.authorize.request.AuthorizeRequest
import test.p00.data.repository.countries.CountriesRepository
import test.p00.data.repository.countries.CountriesRepositoryFactory
import test.p00.domain.abs.Interactor

/**
 * Created by Peter Bukhal on 5/20/18.
 */
class SignUpInteractor(
        private val countries: CountriesRepository = CountriesRepositoryFactory.create(),
        private val authorize: Authorize = Authorize.api): Interactor {

    /**
     *
     */
    fun fetchDefaultCountry(): Observable<Country> =
            countries.fetchDefault()

    /**
     *
     */
    fun confirmPhoneNumber(countryCode: String, phoneNumber: String): Completable =
            authorize.authorize(AuthorizeRequest(countryCode, phoneNumber))
                     .map { response -> response.confirmationRetryDelay }
                     .ignoreElements()

    /**
     *
     */
    fun confirmCode(confirmationCode: Int): Observable<String> =
            authorize.authorizeConfirmation(AuthorizeConfirmationRequest(confirmationCode))
                     .map { response -> response.accessToken }


}