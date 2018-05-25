package test.p00.presentation.activity.abs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.support.DaggerAppCompatActivity
import test.p00.presentation.abs.Router

abstract class AbsActivity : DaggerAppCompatActivity(), Router.Delegate {

    private var mIsFragmentTransactionsAllowed: Boolean = false

    override fun checkIfRoutingAvailable(): Boolean {
        return mIsFragmentTransactionsAllowed
    }

    override fun onResumeFragments() {
        mIsFragmentTransactionsAllowed = true

        super.onResumeFragments()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        mIsFragmentTransactionsAllowed = false

        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mIsFragmentTransactionsAllowed = true
    }


}