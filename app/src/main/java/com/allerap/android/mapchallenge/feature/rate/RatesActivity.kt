package com.allerap.android.mapchallenge.feature.rate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.allerap.android.mapchallenge.R
import com.allerap.android.mapchallenge.domain.entities.Rate
import com.allerap.android.mapchallenge.feature.base.BaseActivity
import kotlinx.android.synthetic.main.activity_rates.*
import java.io.Serializable
import javax.inject.Inject

fun Context.ratesIntent(rates: List<Rate>): Intent =
        Intent(this, RatesActivity::class.java).apply {
            putExtra(EXTRA_RATES, rates as Serializable)
        }

internal const val EXTRA_RATES = "extra_rates"

class RatesActivity : BaseActivity(), RatesView {

    @Inject
    lateinit var presenter: RatesPresenter

    @Inject
    lateinit var adapter: RatesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rates)

        configView()
    }

    private fun configView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
