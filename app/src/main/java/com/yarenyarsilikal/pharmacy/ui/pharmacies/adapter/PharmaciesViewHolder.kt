package com.yarenyarsilikal.pharmacy.ui.pharmacies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yarenyarsilikal.pharmacy.R
import com.yarenyarsilikal.pharmacy.network.model.PharmacyModel

class PharmaciesViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pharmacy, parent, false)
    ) {

    fun bind(pharmacyModel: PharmacyModel) {
        //TODO : itemlar set et
    }
}