package com.yarenyarsilikal.pharmacy.ui.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yarenyarsilikal.pharmacy.R
import com.yarenyarsilikal.pharmacy.network.model.RssItem
import kotlinx.android.synthetic.main.item_news.view.*

class NewsViewHolder(val parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
    ) {

    fun bind(rssItem: RssItem, onItemClickListener: (String) -> Unit) {

        itemView.setOnClickListener { rssItem.link?.let { it1 -> onItemClickListener(it1) } }

        var firstIndex = 10000
        var secondIndex = 10000
        rssItem.description?.forEachIndexed { i, c ->
            if (c == '\"') {
                if (firstIndex == 10000) {
                    firstIndex = i
                } else {
                    secondIndex = i
                }
            }
        }
        Glide
            .with(parent.context)
            .load(rssItem.description?.subSequence(firstIndex + 1, secondIndex))
            .fitCenter()
            .into(itemView.imageViewNews)
        itemView.textViewTitle.text = rssItem.title
        itemView.textViewDate.text = rssItem.date
    }
}