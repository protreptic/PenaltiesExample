package test.p00.presentation.impl

import android.os.Bundle
import test.p00.auxiliary.extensions.pushRoot
import test.p00.presentation.impl.abs.AbsContainer
import test.p00.presentation.signup.impl.SignUpFragment

class DefaultContainer : AbsContainer() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.pushRoot(SignUpFragment.newInstance())
            //supportFragmentManager.pushRoot(LauncherFragment.newInstance())
        }
    }

}