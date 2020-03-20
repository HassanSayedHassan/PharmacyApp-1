package com.yarenyarsilikal.pharmacy.ui.news.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yarenyarsilikal.pharmacy.network.model.RssItem

class NewsAdapter(
    private val list: List<RssItem>,
    private val onItemClickListener: (String) -> Unit
) :
    RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position], onItemClickListener)
    }
}