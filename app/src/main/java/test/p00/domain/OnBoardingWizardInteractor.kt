package test.p00.domain

import io.reactivex.Observable
import test.p00.data.model.user.DriverLicense
import test.p00.data.model.user.Vehicle
import test.p00.data.repository.user.UserRepository
import test.p00.data.repository.user.UserRepositoryFactory
import java.util.regex.Pattern.*

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
                    .flatMap { validateVehicleLicense(it) }
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

    private val D1 = "[АВЕКМНОРСТУХABEKMHOPCTYX]"
    private val C1 = "[0-9]"

    private val PATTERN_DRIVER_LICENSE_NUMBER = "$C1{2}($D1|$C1){2}$C1{6}"  //ЦЦ ББ ЦЦЦЦЦЦ
    private val PATTERN_VEHICLE_NUMBER_AUTO = "$D1$C1{3}$D1{2}$C1{2,3}"     //Б ЦЦЦ ББ ЦЦ(Ц)
    private val PATTERN_VEHICLE_NUMBER_MOTO = "$C1{4}$D1{2}$C1{2,3}"        //ЦЦЦЦ ББ ЦЦ(Ц)
    private val PATTERN_VEHICLE_LICENSE_NUMBER = "$C1{2}$D1{2}$C1{6}"       //ЦЦ ББ ЦЦЦЦЦЦ

    private fun removeBlanksAndUpperCase(string: String) =
            string.trim().toUpperCase().replace(" ", "")

    fun validateDriver(number: String): Observable<Boolean> =
        Observable.just(number)
                  .flatMap {
                      if (matches(PATTERN_DRIVER_LICENSE_NUMBER, removeBlanksAndUpperCase(number))) {
                            Observable.just(true)
                      } else {
                          Observable.just(false)
                      } }

    fun validateVehicle(number: String): Observable<Boolean> =
            Observable.just(number)
                    .flatMap {
                        if (matches(PATTERN_VEHICLE_NUMBER_AUTO, removeBlanksAndUpperCase(number)) ||
                                matches(PATTERN_VEHICLE_NUMBER_MOTO, removeBlanksAndUpperCase(number))) {
                            Observable.just(true)
                        } else {
                            Observable.just(false)
                        } }

    fun validateVehicleLicense(number: String): Observable<Boolean> =
            Observable.just(number)
                    .flatMap {
                        if (matches(PATTERN_VEHICLE_LICENSE_NUMBER, removeBlanksAndUpperCase(number))) {
                            Observable.just(true)
                        } else {
                            Observable.just(false)
                        } }

}