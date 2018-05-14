package test.p00.presentation.countries.impl

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import io.reactivex.schedulers.Schedulers
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.activity.abs.AbsFragment
import test.p00.presentation.countries.CountriesPresenter
import test.p00.presentation.countries.CountriesView
import test.p00.presentation.countries.impl.adapter.CountriesAdapter
import test.p00.presentation.model.countries.CountryModel

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class CountriesFragment : AbsFragment(), CountriesView, CountriesAdapter.Delegate {

    companion object {

        const val FRAGMENT_TAG = "tag_CountriesFragment"

        fun newInstance(): Fragment = CountriesFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    private val presenter: CountriesPresenter by lazy {
        CountriesPresenterImpl()
    }

    override val targetLayout = R.layout.view_countries

    private val countries: RecyclerView by bindView(R.id.vCountries)
    private val countriesAdapter by lazy { CountriesAdapter(delegate = this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countries.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        presenter.attachView(this)
    }

    override fun showCountries(countries: List<CountryModel>) {
        disposables.add(
            countriesAdapter
                .changeData(countries)
                .subscribeOn(Schedulers.computation())
                .subscribe())
    }

    override fun onCountryPicked(country: CountryModel) {
        targetFragment?.onActivityResult(0, 0, Intent())
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}