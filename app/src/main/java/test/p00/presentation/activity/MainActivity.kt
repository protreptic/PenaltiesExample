package test.p00.presentation.activity

import android.os.Bundle
import test.p00.presentation.activity.abs.AbsActivity
import test.p00.presentation.signup.impl.SignUpFragment

class MainActivity : AbsActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(android.R.id.content,
                            SignUpFragment.newInstance(),
                            SignUpFragment.FRAGMENT_TAG)
                    .commit()

//            supportFragmentManager
//                    .beginTransaction()
//                    .pushRoot(android.R.id.content,
//                            LauncherFragment.newInstance(),
//                            LauncherFragment.FRAGMENT_TAG)
//                    .commit()
        }
    }

}