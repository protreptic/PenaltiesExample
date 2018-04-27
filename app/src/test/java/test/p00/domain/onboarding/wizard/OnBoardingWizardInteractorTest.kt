package test.p00.domain.onboarding.wizard

import io.reactivex.Observable.just
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks
import test.p00.data.model.user.User
import test.p00.data.repository.user.UserRepository
import test.p00.domain.launcher.wizard.OnBoardingWizardInteractor

class OnBoardingWizardInteractorTest {

    companion object {

        private const val TEST_USER_NAME = "testName"

        private val VEHICLE_NUMBER_VALID =
                listOf("с065мк78", " c065mk78", "c065mk750", " 8776 АЕ 64", "6429 ре 647", "ва456 777", "xc123 77")
        private val VEHICLE_NUMBER_INVALID =
                listOf("я065mk750", "я065mkk750", "я06mk750", "123", "апвdf1123", "", " ")

        private val VEHICLE_LICENSE_NUMBER_VALID =
                listOf("23мн734534")
        private val VEHICLE_LICENSE_NUMBER_INVALID =
                listOf("23мн73453n")

        private val DRIVER_LICENSE_NUMBER_VALID =
                listOf("23мн734534", "2356734534", "23 мн 734534")
        private val DRIVER_LICENSE_NUMBER_INVALID =
                listOf("23мн73453n", "03mn73453n")

    }

    private val userRepository = mock(UserRepository::class.java)
    private val sut = OnBoardingWizardInteractor(userRepository)

    @Before
    fun setUp() {
        initMocks(this)
    }

    @Test
    fun validateDriver() {
        DRIVER_LICENSE_NUMBER_VALID.forEach { item ->
            sut.validateDriver(item).test()
                    .assertValue(true) }

        DRIVER_LICENSE_NUMBER_INVALID.forEach { item ->
            sut.validateDriver(item).test()
                    .assertValue(false) }
    }

    @Test
    fun checkIfDriverSuccessfullyAdded() {
        val testUser = User()
        val testDriverName = "testName"
        val testDriverLicenseNumber = DRIVER_LICENSE_NUMBER_VALID.first()

        `when`(userRepository.fetch()).thenReturn(just(testUser))
        `when`(userRepository.retain(testUser)).thenReturn(just(testUser))

        sut.addDriver(testDriverName, testDriverLicenseNumber)
                .test()
                .assertValue(true)

        assertThat(testUser.drivers.size, `is`(1))
        assertThat(testUser.drivers.first()?.name, `is`(testDriverName))
        assertThat(testUser.drivers.first()?.registrationNumber, `is`(testDriverLicenseNumber))
        assertThat(testUser.vehicles.size, `is`(0))

        inOrder(userRepository).apply {
            this.verify(userRepository, times(1)).fetch()
            this.verify(userRepository, times(1)).retain(testUser)
            this.verifyNoMoreInteractions()
        }
    }

    @Test
    fun checkIfDriverAddedOnlyOnce() {
        val testUser = User()
        val testDriverName = "testName"

        val calls = DRIVER_LICENSE_NUMBER_VALID.size

        `when`(userRepository.fetch()).thenReturn(just(testUser))
        `when`(userRepository.retain(testUser)).thenReturn(just(testUser))

        for (idx in 0 until calls) {
            sut.addDriver(testDriverName, DRIVER_LICENSE_NUMBER_VALID[idx])
                    .test()
                    .assertValue(true)
        }

        assertThat(testUser.drivers.size, `is`(1))
        assertThat(testUser.drivers.first()?.name, `is`(testDriverName))
        assertThat(testUser.drivers.first()?.registrationNumber, `is`(DRIVER_LICENSE_NUMBER_VALID.last()))
        assertThat(testUser.vehicles.size, `is`(0))
    }

    @Test
    fun checkIfDriverUnsuccessfullyAdded() {
        val testUser = User()
        val testDriverName = TEST_USER_NAME
        val testDriverLicenseNumber = DRIVER_LICENSE_NUMBER_INVALID.first()

        `when`(userRepository.fetch()).thenReturn(just(testUser))
        `when`(userRepository.retain(testUser)).thenReturn(just(testUser))

        sut.addDriver(testDriverName, testDriverLicenseNumber).test()
                .assertValue(false)

        assertThat(testUser.drivers.size, `is`(0))
        assertThat(testUser.vehicles.size, `is`(0))

        inOrder(userRepository).apply {
            this.verifyNoMoreInteractions()
        }
    }

    @Test
    fun validateVehicle() {
        VEHICLE_NUMBER_VALID.forEach { item ->
            sut.validateVehicle(item).test().assertValue(true) }
        VEHICLE_NUMBER_INVALID.forEach { item ->
            sut.validateVehicle(item).test().assertValue(false) }
    }

    @Test
    fun checkIfVehicleSuccessfullyAdded() {
        val testUser = User()
        val testVehicleName = TEST_USER_NAME
        val testVehicleNumber = VEHICLE_NUMBER_VALID[0]

        `when`(userRepository.fetch()).thenReturn(just(testUser))
        `when`(userRepository.retain(testUser)).thenReturn(just(testUser))

        sut.addVehicle(testVehicleName, testVehicleNumber)
                .test()
                .assertValue(true)

        assertThat(testUser.drivers.size, `is`(0))
        assertThat(testUser.vehicles.size, `is`(1))
        assertThat(testUser.vehicles.first()?.name, `is`(testVehicleName))
        assertThat(testUser.vehicles.first()?.number, `is`(testVehicleNumber))

        inOrder(userRepository).apply {
            this.verify(userRepository, times(1)).fetch()
            this.verify(userRepository, times(1)).retain(testUser)
            this.verifyNoMoreInteractions()
        }
    }

    @Test
    fun checkIfVehicleUnsuccessfullyAdded() {
        val testUser = User()
        val testVehicleName = TEST_USER_NAME
        val testVehicleNumber = VEHICLE_NUMBER_INVALID.first()

        `when`(userRepository.fetch()).thenReturn(just(testUser))
        `when`(userRepository.retain(testUser)).thenReturn(just(testUser))

        sut.addVehicle(testVehicleName, testVehicleNumber).test()
                .assertValue(false)

        assertThat(testUser.drivers.size, `is`(0))
        assertThat(testUser.vehicles.size, `is`(0))

        inOrder(userRepository).apply {
            this.verifyNoMoreInteractions()
        }
    }

    @Test
    fun validateVehicleLicense() {
        VEHICLE_LICENSE_NUMBER_VALID.forEach { item ->
            sut.validateVehicleLicense(item).test()
                    .assertValue(true) }

        VEHICLE_LICENSE_NUMBER_INVALID.forEach { item ->
            sut.validateVehicleLicense(item).test()
                    .assertValue(false) }
    }

}