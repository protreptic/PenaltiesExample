package test.p00.domain.launcher.wizard

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Observable.*
import test.p00.data.model.user.Driver
import test.p00.data.model.user.Vehicle
import test.p00.data.repository.settings.SettingsRepository
import test.p00.data.repository.settings.SettingsRepositoryFactory
import test.p00.data.repository.user.UserRepository
import test.p00.data.repository.user.UserRepositoryFactory
import test.p00.domain.abs.Interactor
import java.util.regex.Pattern.*

class OnBoardingWizardInteractor(
        private val userRepository: UserRepository =
                                    UserRepositoryFactory.create(),
        private val settingsRepository: SettingsRepository =
                                        SettingsRepositoryFactory.create()) : Interactor {

    fun addDriver(rawName: String, number: String): Observable<Boolean> =
                validateDriver(number)
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
                                        drivers.add(Driver().apply {
                                            name = rawName
                                            registrationNumber = number
                                        })
                                    }) }
                        .flatMap { just(true) }
                    else -> just(false) } }

    fun addVehicle(rawName: String, rawNumber: String): Observable<Boolean> =
                validateVehicle(rawNumber)
                .flatMap { valid ->
                    when (valid) {
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
                        else -> just(false) } }

    fun addVehicleLicense(rawNumber: String): Observable<Boolean> =
                validateVehicleLicense(rawNumber)
                .flatMap { valid ->
                    when (valid) {
                        true -> userRepository
                            .fetch()
                            .flatMap { user -> userRepository.retain(
                                        user.apply {
                                            if (vehicles.isNotEmpty()) {
                                                vehicles[0]?.registrationNumber = rawNumber
                                            }
                                        }) }
                            .flatMap { just(true) }
                        else -> just(true) } }

    fun markOnBoardingWizardAsShown(): Completable =
            settingsRepository.fetch()
                    .map { settings -> settingsRepository.retain(
                            settings.apply { wasOnBoardingWizardShown = true }) }
                    .ignoreElements()

    companion object {

        private const val C1 = "[АВЕКМНОРСТУХABEKMHOPCTYX]"
        private const val C2 = "[0-9]"

        /**
         * Регистрационные знаки частных транспортных средств. Введены в 1993 году.
         * Сначала имели формат — буква — 3 цифры — 2 буквы, код региона, но отсутствовал флаг.
         * Флаг на номерах появляется в 1994 году, хотя в некоторых регионах, например,
         * в Татарстане, Башкортостане, Санкт-Петербурге и Приморье,
         * номера 1993 года (иногда параллельно) выдавались до первой половины 2000-х гг.
         * Формат знаков — 3 буквы, 3 цифры. Буквы означают серию номерного знака, а цифры — номер.
         * В правой части номерного знака помещается код региона регистрации и
         * флага России с надписью RUS (см. ниже таблицу кодов регионов).
         */
        private const val PATTERN_VEHICLE_NUMBER_AUTO = "$C1$C2{3}$C1{2}$C2{2,3}" //Б ЦЦЦ ББ ЦЦ(Ц)

        /**
         * Регистрационные знаки, устанавливаемые на мотоциклы, мотороллеры, мопеды и мотонарты.
         * Изготавливаются из квадратной светоотражающей пластины, с использованием цифр и букв
         * чёрного цвета (4 цифры в верхней строке, две буквы в нижней).
         * Номер региона регистрации — в нижнем правом углу, чуть выше — надпись RUS.
         */
        private const val PATTERN_VEHICLE_NUMBER_MOTO = "$C2{4}$C1{2}$C2{2,3}" //ЦЦЦЦ ББ ЦЦ(Ц)

        /**
         * Регистрационные знаки транспортных средств, используемых для легковых такси,
         * транспортных средств, оборудованных для перевозок более восьми человек
         * (кроме случаев, если указанные перевозки осуществляются по заказам либо для
         * обеспечения собственных нужд юридического лица или индивидуального предпринимателя)[3].
         * Введены 1 марта 2002 года. Устанавливаются на автобусах, такси и на транспортных
         * средствах, осуществляющих перевозку пассажиров как на муниципальных автобусах,
         * так и на коммерческой основе («маршрутках») Формат: 2 буквы — 3 цифры.
         */
        private const val PATTERN_VEHICLE_NUMBER_COMMERCIAL = "$C1{2}$C2{3}$C2{2,3}" //ББ ЦЦЦ  ЦЦ(Ц)

        private const val PATTERN_DRIVER_LICENSE_NUMBER = "$C2{2}($C1|$C2){2}$C2{6}" //ЦЦ ББ ЦЦЦЦЦЦ
        private const val PATTERN_VEHICLE_LICENSE_NUMBER = "$C2{2}($C1|$C2){2}$C2{6}" //ЦЦ ББ ЦЦЦЦЦЦ

    }

    private fun prepareInput(string: String) =
            string.trim().toUpperCase().replace(" ", "")

    fun validateVehicle(number: String): Observable<Boolean> =
        just(matches(PATTERN_VEHICLE_NUMBER_AUTO, prepareInput(number)) ||
             matches(PATTERN_VEHICLE_NUMBER_MOTO, prepareInput(number)) ||
             matches(PATTERN_VEHICLE_NUMBER_COMMERCIAL, prepareInput(number)))

    fun validateVehicleLicense(number: String): Observable<Boolean> =
        just(matches(PATTERN_VEHICLE_LICENSE_NUMBER, prepareInput(number)))

    fun validateDriver(number: String): Observable<Boolean> =
        just(matches(PATTERN_DRIVER_LICENSE_NUMBER, prepareInput(number)))

}