package com.yarenyarsilikal.pharmacy.ui.pharmacies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yarenyarsilikal.pharmacy.R
import com.yarenyarsilikal.pharmacy.network.model.PharmacyModel
import kotlinx.android.synthetic.main.item_pharmacy.view.*

class PharmaciesViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pharmacy, parent, false)
    ) {

    fun bind(
        pharmacyModel: PharmacyModel,
        onMapClickListener: (String) -> Unit,
        onPhoneClickListener: (String) -> Unit
    ) {
        itemView.setOnClickListener {
            if (pharmacyModel.isExpanded) {
                itemView.clsecondcontainer.visibility = View.GONE
                pharmacyModel.isExpanded = false

            } else {
                itemView.clsecondcontainer.visibility = View.VISIBLE
                pharmacyModel.isExpanded = true
            }
        }
        itemView.textViewName.text = pharmacyModel.name
        itemView.textViewDistrict.text = pharmacyModel.dist
        itemView.textViewTel.text = pharmacyModel.phone
        itemView.textViewAddress.text = pharmacyModel.address
        itemView.imageButtonMap.setOnClickListener { onMapClickListener(pharmacyModel.loc) }
        itemView.textViewTel.setOnClickListener { onPhoneClickListener(pharmacyModel.phone) }
        itemView.clsecondcontainer.visibility =
            if (pharmacyModel.isExpanded) View.VISIBLE else View.GONE
    }

}