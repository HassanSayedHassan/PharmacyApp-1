package com.yarenyarsilikal.pharmacy.network.model

import com.google.gson.annotations.SerializedName

data class City(

    @SerializedName("il") val il: String,
    @SerializedName("plaka") val plaka: Int,
    @SerializedName("ilceleri") val ilceleri: List<String>
)

data class CityList(val cities: List<City>)