package test.p00.activity

import android.os.Bundle
import test.p00.activity.abs.AbsActivity
import test.p00.presentation.launcher.impl.LauncherFragment

class MainActivity : AbsActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(android.R.id.content,
                            LauncherFragment.newInstance(),
                            LauncherFragment.FRAGMENT_TAG)
                    .commit()
        }
    }

}