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

    private val A1 = "[АВЕКМНОРСТУХABEKMHOPCTYX]"
    private val B2 = "[0-9]"

    /**
     * Регистрационные знаки частных транспортных средств. Введены в 1993 году.
     * Сначала имели формат — буква — 3 цифры — 2 буквы, код региона, но отсутствовал флаг.
     * Флаг на номерах появляется в 1994 году, хотя в некоторых регионах, например,
     * в Татарстане, Башкортостане, Санкт-Петербурге и Приморье,
     * номера 1993 года (иногда параллельно) выдавались до первой половины 2000-х гг.
     * Формат знаков — 3 буквы, 3 цифры. Буквы означают серию номерного знака, а цифры — номер.
     * В правой части номерного знака помещается код региона регистрации и
     * флага России с надписью RUS (см. ниже таблицу кодов регионов).
     *
     * До 1 июля 2008 года регистрационный знак, выданный грузовому автомобилю или автобусу,
     * должен был быть продублирован на задней поверхности ТС крупным шрифтом.
     * С 1 июля 2008 года данное требование отменено[2][нет в источнике].
     */
    private val PATTERN_VEHICLE_NUMBER_AUTO = "$A1$B2{3}$A1{2}$B2{2,3}" //Б ЦЦЦ ББ ЦЦ(Ц)

    /**
     * Регистрационные знаки, устанавливаемые на мотоциклы, мотороллеры, мопеды и мотонарты.
     * Изготавливаются из квадратной светоотражающей пластины, с использованием цифр и букв
     * чёрного цвета (4 цифры в верхней строке, две буквы в нижней).
     * Номер региона регистрации — в нижнем правом углу, чуть выше — надпись RUS.
     */
    private val PATTERN_VEHICLE_NUMBER_MOTO = "$B2{4}$A1{2}$B2{2,3}" //ЦЦЦЦ ББ ЦЦ(Ц)

    private val PATTERN_DRIVER_LICENSE_NUMBER = "$B2{2}($A1|$B2){2}$B2{6}" //ЦЦ ББ ЦЦЦЦЦЦ
    private val PATTERN_VEHICLE_LICENSE_NUMBER = "$B2{2}($A1|$B2){2}$B2{6}" //ЦЦ ББ ЦЦЦЦЦЦ

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