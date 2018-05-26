package test.p00.presentation

import android.support.v4.app.FragmentManager
import test.p00.presentation.abs.Router

/**
 * Created by Peter Bukhal on 5/24/18.
 */
class DefaultRouter(
        override val fragmentManager: FragmentManager?,
        override val delegate: Router.Delegate): Router