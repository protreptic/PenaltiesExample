package test.p00.data.model.user

import test.p00.data.model.user.profile.UserProfile

/**
 * Created by Peter Bukhal on 5/20/18.
 */
data class UserNew(val id: Int = 0, val accessToken: String, val pushToken: String, val isDefault: Boolean, val profile: UserProfile)