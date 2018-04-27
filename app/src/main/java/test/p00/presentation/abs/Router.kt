package test.p00.presentation.abs

/**
 * Created by Peter Bukhal on 4/24/18.
 */
interface Router {

    interface Delegate {

        fun checkIfRoutingAvailable(): Boolean

    }

    fun toHome()

}