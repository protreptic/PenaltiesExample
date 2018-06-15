package test.p00.presentation.countries.impl.adapter

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import test.p00.R
import test.p00.presentation.countries.CountriesView.SortBy
import test.p00.presentation.countries.impl.SortByModel

/**
 * Created by Peter Bukhal on 6/9/18.
 */
class CountriesSortByAdapter(
        context: Context?,
        var delegate: Delegate? = null):
            ArrayAdapter<SortByModel>(context, R.layout.view_countries__sort_by, R.id.sort_by_name),
            AdapterView.OnItemSelectedListener {

    interface Delegate {

        /**
         * Событие наступает при изменении
         * пользователем типа сортировки.
         *
         * @param sortBy тип сортировки
         */
        fun onSortByChanged(sortBy: SortBy)

    }

    var selectedSortBy = SortBy.NAME

    fun changeData(newData: List<SortByModel>) {
        clear()
        addAll(newData)
        notifyDataSetChanged()
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        selectedSortBy = getItem(position).sortBy

        delegate?.onSortByChanged(selectedSortBy)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

}