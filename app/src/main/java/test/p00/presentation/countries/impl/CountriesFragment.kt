package test.p00.presentation.countries.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotterknife.bindView
import test.p00.R
import test.p00.data.repository.countries.CountriesRepository
import test.p00.presentation.auxiliary.TopPaddingItemDecoration
import test.p00.presentation.countries.CountriesPresenter
import test.p00.presentation.countries.CountriesView
import test.p00.presentation.countries.CountriesView.*
import test.p00.presentation.countries.impl.adapter.CountriesAdapter
import test.p00.presentation.countries.impl.adapter.CountriesSortByAdapter
import test.p00.presentation.impl.abs.AbsView
import test.p00.presentation.model.countries.CountryModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class CountriesFragment : AbsView(), CountriesView,
        CountriesAdapter.Delegate, CountriesSortByAdapter.Delegate {

    companion object {

        const val FRAGMENT_TAG = "fragment_tag_countries"

        fun newInstance(): Fragment = CountriesFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    @Inject lateinit var countriesRepository: CountriesRepository

    private val presenter: CountriesPresenter by lazy {
        CountriesPresenterImpl(schedulers, countriesRepository, router, bus)
    }

    override val targetLayout = R.layout.view_countries

    private val countries: RecyclerView by bindView(R.id.vCountries)
    private val countriesAdapter = CountriesAdapter(delegate = this)

    private val countriesFilter: EditText by bindView(R.id.vCountriesFilter)

    private val countriesSortBy: Spinner by bindView(R.id.countries_sort_by)
    private val countriesSortByAdapter: CountriesSortByAdapter by lazy {
        CountriesSortByAdapter(context, delegate = this@CountriesFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countriesSortBy.apply {
            adapter = countriesSortByAdapter
            onItemSelectedListener = countriesSortByAdapter
        }

        countries.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter

            addItemDecoration(TopPaddingItemDecoration(54))
        }

        disposables.add(
            RxTextView
                .afterTextChangeEvents(countriesFilter)
                .debounce(200L, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map { input -> input.editable().toString() }
                .subscribe({ pattern -> presenter.displayCountries(pattern,
                        countriesSortByAdapter.selectedSortBy) }, { /*  */ }))

        presenter.attachView(this)
    }

    override fun showCountries(countries: List<CountryModel>, sortBy: List<SortByModel>) {
        disposables.add(
            countriesAdapter
                .changeData(countries)
                .doOnSubscribe { countriesSortByAdapter.changeData(sortBy) }
                .subscribe({ this.countries.smoothScrollToPosition(0) }))
    }

    override fun onSortByChanged(sortBy: SortBy) {
        presenter.displayCountries(countriesFilter.text.toString(), sortBy)
    }

    override fun onCountryPicked(country: CountryModel) {
        presenter.pickCountry(country)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}