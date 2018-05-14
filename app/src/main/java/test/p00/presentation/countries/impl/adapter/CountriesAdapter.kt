package test.p00.presentation.countries.impl.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.util.DiffUtil.*
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.countries.impl.adapter.CountriesAdapter.CountryViewHolder
import test.p00.presentation.model.countries.CountryModel

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class CountriesAdapter(
        private var data: List<CountryModel> = listOf(),
        private var delegate: Delegate? = null) : RecyclerView.Adapter<CountryViewHolder>() {

    class Diff(private val old: List<CountryModel>, private val new: List<CountryModel>) : DiffUtil.Callback() {

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

    interface Delegate {

        /**
         * Событие наступает когда пользователь
         * выбирает страну из списка
         * стран.
         *
         * @param country выбранная страна
         */
        fun onCountryPicked(country: CountryModel)

    }

    fun changeData(newData: List<CountryModel>): Completable =
            Observable.just(newData)
                    .map { calculateDiff(Diff(data, newData), false) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext { data = newData }
                    .doOnNext { result -> result.dispatchUpdatesTo(this) }
                    .ignoreElements()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CountryViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_countries_country, parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = data[position]

        holder.bindCountry(country)
        holder.itemView.setOnClickListener {
            delegate?.onCountryPicked(country)
        }
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val vFlag: ImageView by bindView(R.id.vFlag)
        private val vName: TextView by bindView(R.id.vName)
        private val vPhoneCode: TextView by bindView(R.id.vPhoneCode)

        fun bindCountry(country: CountryModel) {
            Glide.with(itemView.context)
                    .load("file:///android_asset/storage/countries/flags/${country.iso}.png")
                    //.apply(RequestOptions.circleCropTransform())
                    .into(vFlag)

            vName.text = country.name
            vPhoneCode.text = String.format("+%d", country.phoneCode)
        }

    }

}