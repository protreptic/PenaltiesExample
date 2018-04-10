package test.p00.domain

import android.text.TextUtils
import io.reactivex.Observable
import test.p00.data.model.user.DriverLicense
import test.p00.data.model.user.Vehicle
import test.p00.data.repository.user.UserRepository
import test.p00.data.repository.user.UserRepositoryFactory

class OnBoardingWizardInteractor(
        private val userRepository: UserRepository =
                                    UserRepositoryFactory.create()) {

    fun tryAddDriver(rawName: String, rawNumber: String): Observable<Boolean> =
            Observable.just(rawNumber)
                    .flatMap { validateDriver(it) }
                    .flatMap { valid ->
                        if (valid) {
                            userRepository
                                    .fetch()
                                    .flatMap { user -> userRepository.retain(
                                            user.apply {
                                                drivers.add(DriverLicense().apply {
                                                    name = rawName
                                                    registrationNumber = rawNumber
                                                })
                                            }) }
                                    .flatMap { Observable.just(true) }
                        } else {
                            Observable.just(false)
                        } }

    fun tryAddVehicle(rawName: String, rawNumber: String): Observable<Boolean> =
            Observable.just(rawNumber)
                    .flatMap { validateVehicle(it) }
                    .flatMap { valid ->
                        if (valid) {
                            userRepository
                                    .fetch()
                                    .flatMap { user -> userRepository.retain(
                                               user.apply {
                                                   vehicles.add(Vehicle().apply {
                                                       name = rawName
                                                       number = rawNumber
                                                   })
                                               }) }
                                    .flatMap { Observable.just(true) }
                        } else {
                            Observable.just(false)
                        } }

    fun tryAddVehicleLicense(rawNumber: String): Observable<Boolean> =
            Observable.just(rawNumber)
                    .flatMap { validateVehicle(it) }
                    .flatMap { valid ->
                        if (valid) {
                            userRepository
                                    .fetch()
                                    .flatMap { user -> userRepository.retain(
                                            user.apply {
                                                if (vehicles.isNotEmpty()) {
                                                    vehicles[0]!!.registrationNumber = rawNumber
                                                }
                                            }) }
                                    .flatMap { Observable.just(true) }
                        } else {
                            Observable.just(false)
                        } }

    fun validateDriver(number: String): Observable<Boolean> =
        Observable.just(number)
                  .flatMap {
                      /*
                       * Считаем что пользователь ввел валидный номер,
                       * если он не пустой и ровно 10 цифр.
                       */
                      if (number.isNotEmpty() &&
                              TextUtils.isDigitsOnly(number) &&
                                    number.length == 10) {
                            Observable.just(true)
                      } else {
                          Observable.just(false)
                      } }

    fun validateVehicle(number: String): Observable<Boolean> =
            Observable.just(number)
                    .flatMap {
                        /*
                         * Считаем что пользователь ввел валидный номер,
                         * если он не пустой и ровно 10 цифр.
                         */
                        if (number.isNotEmpty() &&
                                TextUtils.isDigitsOnly(number) &&
                                number.length == 10) {
                            Observable.just(true)
                        } else {
                            Observable.just(false)
                        } }

    fun validateVehicleLicense(number: String): Observable<Boolean> =
            Observable.just(number)
                    .flatMap {
                        /*
                         * Считаем что пользователь ввел валидный номер,
                         * если он не пустой и ровно 10 цифр.
                         */
                        if (number.isNotEmpty() &&
                                TextUtils.isDigitsOnly(number) &&
                                number.length == 10) {
                            Observable.just(true)
                        } else {
                            Observable.just(false)
                        } }

}