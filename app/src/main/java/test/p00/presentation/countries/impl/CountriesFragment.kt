package test.p00.presentation.countries.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import kotterknife.bindView
import test.p00.R
import test.p00.data.repository.countries.CountriesRepository
import test.p00.presentation.impl.abs.AbsView
import test.p00.presentation.countries.CountriesPresenter
import test.p00.presentation.countries.CountriesView
import test.p00.presentation.countries.impl.adapter.CountriesAdapter
import test.p00.presentation.model.countries.CountryModel
import test.p00.presentation.auxiliary.TopPaddingItemDecoration
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class CountriesFragment : AbsView(), CountriesView, CountriesAdapter.Delegate {

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
    private val countriesFilter: EditText by bindView(R.id.vCountriesFilter)
    private val countriesAdapter = CountriesAdapter(delegate = this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countries.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter

            addItemDecoration(TopPaddingItemDecoration(54))
        }

        disposables.add(
            RxTextView
                .afterTextChangeEvents(countriesFilter)
                .doOnSubscribe { countriesFilter.requestFocus() }
                .map { input -> input.editable().toString() }
                .subscribe({ pattern -> presenter.displayCountries(pattern) }, {  }))

        presenter.attachView(this)
    }

    override fun showCountries(countries: List<CountryModel>) {
        disposables.add(
            countriesAdapter
                .changeData(countries)
                .subscribe())
    }

    override fun onCountryPicked(country: CountryModel) {
        presenter.pickCountry(country)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}