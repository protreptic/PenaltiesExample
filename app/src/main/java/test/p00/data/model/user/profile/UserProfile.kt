package test.p00.data.model.user.profile

/**
 * Created by Peter Bukhal on 5/20/18.
 */
data class UserProfile(
        val id: Int = 0,
        val userId: Int = 0,
        var firstName: String = "",
        var middleName: String = "",
        var lastName: String = "",
        var birthDate: String = "",
        var phoneNumber: String = "",
        var email: String = "")