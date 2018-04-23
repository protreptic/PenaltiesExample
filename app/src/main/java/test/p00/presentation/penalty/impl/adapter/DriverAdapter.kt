package test.p00.presentation.penalty.impl.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.penalty.model.DriverModel

class DriverAdapter(private var data: List<DriverModel> = listOf()) : RecyclerView.Adapter<DriverAdapter.DriverViewHolder>() {

    fun setData(newData: List<DriverModel>) {
        data = newData

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            DriverViewHolder(from(parent.context)
                    .inflate(R.layout.view_driver_item, parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        data[position].let { model -> holder.bindModel(model) }
    }

    class DriverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val vDriver: TextView by bindView(R.id.textView4)
        private val vDriverLicense: TextView by bindView(R.id.textView5)

        fun bindModel(model: DriverModel) {
            vDriver.text = model.name
            vDriverLicense.text = model.number
        }

    }

}