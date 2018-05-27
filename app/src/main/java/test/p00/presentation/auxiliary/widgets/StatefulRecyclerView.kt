package test.p00.presentation.auxiliary.widgets

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

/**
 * Created by Peter Bukhal on 5/27/18.
 */
class StatefulRecyclerView : RecyclerView {

    companion object {

        private const val SAVED_SUPER_STATE = "super-state"
        private const val SAVED_LAYOUT_MANAGER = "layout-manager-state"
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    private var mLayoutManagerSavedState: Parcelable? = null

    override fun onSaveInstanceState(): Parcelable {
        return Bundle().apply {
            putParcelable(SAVED_SUPER_STATE, super.onSaveInstanceState())
            putParcelable(SAVED_LAYOUT_MANAGER, layoutManager.onSaveInstanceState())
        }
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state is Bundle) {
            mLayoutManagerSavedState = state.getParcelable(SAVED_LAYOUT_MANAGER)
        }

        super.onRestoreInstanceState((state as Bundle).getParcelable(SAVED_SUPER_STATE))
    }

    private fun restorePosition() {
        if (mLayoutManagerSavedState != null) {
            layoutManager.onRestoreInstanceState(mLayoutManagerSavedState)
            mLayoutManagerSavedState = null
        }
    }

    private val adapterDataObserver = object : AdapterDataObserver() {

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            restorePosition()
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            restorePosition()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            restorePosition()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            restorePosition()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            restorePosition()
        }

        override fun onChanged() {
            restorePosition()
        }

    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        super.setAdapter(adapter)

        this.adapter.registerAdapterDataObserver(adapterDataObserver)
    }

}