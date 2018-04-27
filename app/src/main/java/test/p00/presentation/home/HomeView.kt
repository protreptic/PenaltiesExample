package test.p00.presentation.home

import test.p00.presentation.abs.View
import test.p00.presentation.model.user.UserModel

interface HomeView : View {

    fun showUser(user: UserModel)

}