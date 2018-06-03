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
import test.p00.presentation.auxiliary.dismissKeyboard
import test.p00.presentation.impl.router.DefaultRouter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposables = CompositeDisposable()

        /* предотвратить "прокликивание view" */
        view.setOnClickListener {  }
        view.requestFocus()

        /* скрывать клавиатуру */
        dismissKeyboard(activity)
    }

    override fun transactionAllowed(): Boolean =
            if (activity is Router.Delegate) {
               (activity as Router.Delegate).transactionAllowed() } else false

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(SAVED_STATE, savedState)
    }

    protected val savedState = Bundle()

    open fun restoreState(state: Bundle) {
        /* пустая реализация */
    }

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