package test.p00.presentation.abs

interface Presenter<in T: View> {

    fun attachView(view: T)
    fun detachView()

}