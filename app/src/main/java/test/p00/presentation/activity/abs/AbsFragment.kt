package test.p00.presentation.activity.abs

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable
import test.p00.presentation.abs.Router
import java.io.Serializable

/**
 * Created by Peter Bukhal on 4/19/18.
 */
abstract class AbsFragment : Fragment(), Router.Delegate {

    protected abstract val targetLayout: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(targetLayout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposables = CompositeDisposable()
    }

    override fun checkIfRoutingAvailable(): Boolean =
            if (activity is Router.Delegate) {
               (activity as Router.Delegate).checkIfRoutingAvailable() } else false

    protected lateinit var disposables: CompositeDisposable

    override fun onDestroyView() {
        super.onDestroyView()

        disposables.dispose()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        try {
            data?.apply {
                onResult(data.getSerializableExtra("123"))
            }
        } catch (e: Exception) {
            //
        }
    }

    protected open fun onResult(result: Serializable) {}

}