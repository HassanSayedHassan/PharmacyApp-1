package com.yarenyarsilikal.pharmacy.ui.pharmacies.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yarenyarsilikal.pharmacy.network.model.PharmacyModel

class PharmaciesAdapter(private val list: List<PharmacyModel>) :
    RecyclerView.Adapter<PharmaciesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmaciesViewHolder =
        PharmaciesViewHolder(parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PharmaciesViewHolder, position: Int) {
        holder.bind(list[position])
    }
}