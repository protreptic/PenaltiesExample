package test.p00.presentation.home.impl.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.model.user.VehicleModel

class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val vVehicleName: TextView by bindView(R.id.textView4)
    private val vVehicleNumberAndLicense: TextView by bindView(R.id.textView5)

    fun bind(vehicle: VehicleModel) {
        vVehicleName.text = vehicle.name
        vVehicleNumberAndLicense.text = String.format("РЕГ. НОМЕР ТС: %s\nСВИД. РЕГ. ТС: %s", vehicle.number, vehicle.registrationNumber)
    }

}