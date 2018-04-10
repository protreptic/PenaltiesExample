package test.p00.activity

import android.os.Bundle
import test.p00.activity.base.AbsActivity
import test.p00.presentation.launcher.LauncherFragment

class MainActivity : AbsActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(android.R.id.content,
                            LauncherFragment.newInstance(),
                            LauncherFragment.FRAGMENT_TAG)
                    .commit()
        }
    }

}