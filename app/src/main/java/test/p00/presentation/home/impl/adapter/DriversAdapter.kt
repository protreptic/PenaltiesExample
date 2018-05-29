package test.p00.presentation.home.impl.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.util.DiffUtil.*
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater.from
import android.view.ViewGroup
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import test.p00.R
import test.p00.presentation.model.user.DriverModel

class DriversAdapter(
        private var data: List<DriverModel> = listOf()):
            RecyclerView.Adapter<DriverViewHolder>() {

    class Diff(private val old: List<DriverModel>,
               private val new: List<DriverModel>) : DiffUtil.Callback() {

        override fun getOldListSize() = old.size
        override fun getNewListSize() = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = old[oldItemPosition]
            val new = new[newItemPosition]

            return old.id == new.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = old[oldItemPosition]
            val new = new[newItemPosition]

            return old.id == new.id
        }

    }

    fun changeData(newData: List<DriverModel>): Completable =
            Observable.just(newData)
                      .map { calculateDiff(Diff(data, newData), false) }
                      .observeOn(AndroidSchedulers.mainThread())
                      .doOnNext { data = newData }
                      .doOnNext { result -> result.dispatchUpdatesTo(this) }
                      .ignoreElements()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            DriverViewHolder(from(parent.context)
                    .inflate(R.layout.view_home_driver_item, parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        val driver = data[position]

        holder.bind(driver)
    }

}