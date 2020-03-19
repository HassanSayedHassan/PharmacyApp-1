package com.yarenyarsilikal.pharmacy.ui.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yarenyarsilikal.pharmacy.R
import com.yarenyarsilikal.pharmacy.network.model.RssItem
import kotlinx.android.synthetic.main.item_news.view.*

class NewsViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
    ) {

    fun bind(rssItem: RssItem) {


        itemView.imageViewNews.setImageResource(R.drawable.ic_instagram)
        itemView.textViewTitle.text = rssItem.title
        itemView.textViewDate.text = rssItem.date
    }
}