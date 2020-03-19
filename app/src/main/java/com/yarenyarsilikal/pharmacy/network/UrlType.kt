package com.yarenyarsilikal.pharmacy.network

enum class UrlType {
    DONANIMHABER {
        override fun toString(): String = "https://www.donanimhaber.com/rss/tum/"
    },
    COLLECTAPI {
        override fun toString(): String = "https://collectapi.com/tr/api/health/nobetci-eczane-api"
    }
}