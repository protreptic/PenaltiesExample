package test.p00.presentation.penalty.impl.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.penalty.impl.adapter.VehicleAdapter.VehicleViewHolder
import test.p00.presentation.penalty.model.VehicleModel

class VehicleAdapter(private var data: List<VehicleModel> = listOf()) : RecyclerView.Adapter<VehicleViewHolder>() {

    fun setData(newData: List<VehicleModel>) {
        data = newData

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            VehicleViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_vehicle_item, parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        data[position].let { model -> holder.bindModel(model) }
    }

    class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val vVehicleName: TextView by bindView(R.id.textView4)
        private val vVehicleNumberAndLicense: TextView by bindView(R.id.textView5)

        fun bindModel(model: VehicleModel) {
            vVehicleName.text = model.name
            vVehicleNumberAndLicense.text = String.format("РЕГ. НОМЕР ТС: %s\nСВИД. РЕГ. ТС: %s", model.number, model.registrationNumber)
        }

    }

}