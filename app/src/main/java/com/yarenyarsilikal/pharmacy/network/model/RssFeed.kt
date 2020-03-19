package com.yarenyarsilikal.pharmacy.network.model


data class RssFeed constructor(
    var title: String? = null,
    var link: String? = null,
    var description: String? = null,
    var language: String? = null,
    var rssItems: ArrayList<RssItem>?
)