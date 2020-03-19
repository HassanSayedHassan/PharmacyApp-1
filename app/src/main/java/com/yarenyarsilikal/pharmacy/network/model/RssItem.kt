package com.yarenyarsilikal.pharmacy.network.model


data class RssItem(
    var title: String? = null,
    var description: String? = null,
    var date: String? = null,
    var link: String? = null,
    var enclosure: Enclosure? = null
)

data class Enclosure(var url: String? = null)




