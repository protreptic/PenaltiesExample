package test.p00.presentation.impl.abs

import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import test.p00.presentation.DeepLinkRouter
import test.p00.presentation.Router
import test.p00.presentation.impl.router.DefaultDeepLinkRouter
import test.p00.presentation.impl.router.DefaultRouter

abstract class AbsContainer : DaggerAppCompatActivity(), Router.Delegate {

    private var transactionAllowed: Boolean = false

    override fun transactionAllowed() = transactionAllowed

    override fun onResumeFragments() {
        transactionAllowed = true

        super.onResumeFragments()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        transactionAllowed = false

        super.onSaveInstanceState(outState)
    }

    protected fun handleDeepLinkIntent(intent: Intent): Boolean {
        when (intent.action) {
            Intent.ACTION_VIEW -> {
                deepLinkRouter.openDeepLink(intent.data)

                return false
            }
        }

        return true
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        handleDeepLinkIntent(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        transactionAllowed = true
    }

    protected val router: Router by lazy { DefaultRouter(supportFragmentManager, this) }
    protected val deepLinkRouter: DeepLinkRouter by lazy { DefaultDeepLinkRouter(supportFragmentManager, this) }

}