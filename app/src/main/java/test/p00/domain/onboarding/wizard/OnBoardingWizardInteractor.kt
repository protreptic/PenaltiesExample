package test.p00.domain.onboarding.wizard

import io.reactivex.Observable
import io.reactivex.Observable.*
import test.p00.data.model.user.DriverLicense
import test.p00.data.model.user.Vehicle
import test.p00.data.repository.user.UserRepository
import test.p00.data.repository.user.UserRepositoryFactory
import java.util.regex.Pattern.*

class OnBoardingWizardInteractor(
        private val userRepository: UserRepository =
                                    UserRepositoryFactory.create()) {

    fun tryAddDriver(rawName: String, rawNumber: String): Observable<Boolean> =
            just(rawNumber)
                .flatMap { validateDriver(it) }
                .flatMap { valid -> when (valid) {
                    true -> userRepository
                        .fetch()
                        .flatMap { user -> userRepository.retain(
                                    user.apply {
                                        /*
                                         * Добавляем только последний введенный
                                         * пользователем номер.
                                         */
                                        drivers.clear()
                                        drivers.add(DriverLicense().apply {
                                            name = rawName
                                            registrationNumber = rawNumber
                                        })
                                    }) }
                        .flatMap { just(true) }
                    else -> just(false)
                } }

    fun tryAddVehicle(rawName: String, rawNumber: String): Observable<Boolean> =
            just(rawNumber)
                .flatMap { validateVehicle(it) }
                .flatMap { valid -> when (valid) {
                    true -> userRepository
                        .fetch()
                        .flatMap { user -> userRepository.retain(
                                    user.apply {
                                        /*
                                         * Добавляем только последний введенный
                                         * пользователем номер.
                                         */
                                        vehicles.clear()
                                        vehicles.add(Vehicle().apply {
                                            name = rawName
                                            number = rawNumber
                                        })
                                    }) }
                        .flatMap { just(true) }
                    else -> just(false)
                } }

    fun tryAddVehicleLicense(rawNumber: String): Observable<Boolean> =
            just(rawNumber)
                .flatMap { validateVehicleLicense(it) }
                .flatMap { valid -> when (valid) {
                    true -> userRepository
                        .fetch()
                        .flatMap { user -> userRepository.retain(
                                    user.apply {
                                        if (vehicles.isNotEmpty()) {
                                            vehicles[0]?.registrationNumber = rawNumber
                                        }
                                    }) }
                        .flatMap { just(true) }
                    else -> just(true)
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

    private fun prepareInput(string: String) =
            string.trim().toUpperCase().replace(" ", "")

    fun validateDriver(number: String): Observable<Boolean> =
        just(matches(PATTERN_DRIVER_LICENSE_NUMBER, prepareInput(number)))

    fun validateVehicle(number: String): Observable<Boolean> =
        just(matches(PATTERN_VEHICLE_NUMBER_AUTO, prepareInput(number)) ||
             matches(PATTERN_VEHICLE_NUMBER_MOTO, prepareInput(number)))

    fun validateVehicleLicense(number: String): Observable<Boolean> =
        just(matches(PATTERN_VEHICLE_LICENSE_NUMBER, prepareInput(number)))

}