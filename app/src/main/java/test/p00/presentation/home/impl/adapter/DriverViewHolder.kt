package test.p00.presentation.home.impl.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.model.user.DriverModel

class DriverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val vDriver: TextView by bindView(R.id.textView4)
    private val vDriverLicense: TextView by bindView(R.id.textView5)

    fun bind(driver: DriverModel) {
        vDriver.text = driver.name
        vDriverLicense.text = driver.number
    }

}