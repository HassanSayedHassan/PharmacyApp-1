package com.yarenyarsilikal.pharmacy.network.model

import com.google.gson.annotations.SerializedName

data class PharmacyResponse(

    @SerializedName("success")
    val success: Boolean,

    @SerializedName("result")
    val result: List<PharmacyModel>
)