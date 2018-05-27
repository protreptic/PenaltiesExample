package test.p00.presentation.impl.abs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import test.p00.auxiliary.bus.Bus
import test.p00.auxiliary.reactivex.schedulers.Schedulers
import test.p00.presentation.Router
import test.p00.presentation.impl.DefaultRouter
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 4/19/18.
 */
abstract class AbsView : DaggerFragment(), Router.Delegate {

    companion object {

        private const val SAVED_STATE = "saved_state"

    }

    @Inject protected lateinit var bus: Bus
    @Inject protected lateinit var schedulers: Schedulers

    protected val router: Router by lazy { DefaultRouter(fragmentManager, this) }

    protected abstract val targetLayout: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(targetLayout, container, false)

    protected lateinit var disposables: CompositeDisposable

    override fun onViewCreated(createdView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(createdView, savedInstanceState)

        disposables = CompositeDisposable()

        view?.requestFocus()
    }

    override fun checkIfRoutingAvailable(): Boolean =
            if (activity is Router.Delegate) {
               (activity as Router.Delegate).checkIfRoutingAvailable() } else false

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(SAVED_STATE, savedState)
    }

    protected val savedState = Bundle()

    open fun restoreState(state: Bundle) {}

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        if (savedInstanceState != null) {
            restoreState(savedInstanceState.getParcelable(SAVED_STATE))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        view?.clearFocus()

        disposables.dispose()
    }

}