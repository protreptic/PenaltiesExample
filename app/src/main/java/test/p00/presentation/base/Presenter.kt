package test.p00.presentation.base

interface Presenter<in T: View> {

    fun attachView(view: T)
    fun detachView()

}