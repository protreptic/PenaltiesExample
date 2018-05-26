package test.p00.presentation.home

import test.p00.presentation.Presenter

interface HomePresenter : Presenter<HomeView> {

    fun displayUser()
    fun displayConversations()

}