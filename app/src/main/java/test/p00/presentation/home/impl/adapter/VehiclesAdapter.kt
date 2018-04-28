package test.p00.presentation.home.impl.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.util.DiffUtil.*
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.home.impl.adapter.VehiclesAdapter.VehicleViewHolder
import test.p00.presentation.model.user.VehicleModel

class VehiclesAdapter(private var data: List<VehicleModel> = listOf()) : RecyclerView.Adapter<VehicleViewHolder>() {

    class Diff(private val old: List<VehicleModel>,
               private val new: List<VehicleModel>) : DiffUtil.Callback() {

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

            return old == new
        }

    }

    fun changeData(newData: List<VehicleModel>): Completable =
            Observable.just(newData)
                      .map { calculateDiff(Diff(data, newData), false) }
                      .observeOn(AndroidSchedulers.mainThread())
                      .doOnNext { data = newData }
                      .doOnNext { result -> result.dispatchUpdatesTo(this) }
                      .ignoreElements()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            VehicleViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_home_vehicle_item, parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = data[position]

        holder.bindVehicle(vehicle)
    }

    class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val vVehicleName: TextView by bindView(R.id.textView4)
        private val vVehicleNumberAndLicense: TextView by bindView(R.id.textView5)

        fun bindVehicle(model: VehicleModel) {
            vVehicleName.text = model.name
            vVehicleNumberAndLicense.text = String.format("РЕГ. НОМЕР ТС: %s\nСВИД. РЕГ. ТС: %s", model.number, model.registrationNumber)
        }

    }

}