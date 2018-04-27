package test.p00.presentation.activity.abs

import android.support.v4.app.Fragment
import test.p00.presentation.abs.Router

/**
 * Created by Peter Bukhal on 4/19/18.
 */
abstract class AbsFragment : Fragment(), Router.Delegate {

    override fun checkIfRoutingAvailable(): Boolean =
            if (activity is Router.Delegate) {
                (activity as Router.Delegate).checkIfRoutingAvailable()
            } else false

}