package com.allerap.android.mapchallenge.feature.rate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allerap.android.mapchallenge.R
import com.allerap.android.mapchallenge.domain.entities.Rate
import kotlinx.android.synthetic.main.view_rate.view.*

class RatesAdapter(private val rates: List<Rate>) : RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): RatesViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_rate, parent, false)
        return RatesViewHolder(view)
    }

    override fun getItemCount(): Int = rates.size

    override fun onBindViewHolder(
            holder: RatesViewHolder,
            position: Int
    ) {
        val card = rates[position]
        holder.bind(card)
        holder.itemView
    }

    class RatesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTile = view.tvTitle
        private val tvDescription = view.tvDescription
        private val tvPrice = view.tvPrice
        private val sdvImage = view.sdvImage

        fun bind(rate: Rate) {
            tvTile.text = rate.vehicle.name
            tvDescription.text = rate.vehicle.description
            tvPrice.text = rate.priceFormatted
            sdvImage.setImageURI(rate.vehicle.imageUrl)
        }
    }
}
