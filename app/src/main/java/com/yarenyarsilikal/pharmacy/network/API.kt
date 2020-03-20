package com.yarenyarsilikal.pharmacy.network

import com.yarenyarsilikal.pharmacy.network.model.PharmacyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface API {
    @GET("dutyPharmacy")
    fun getFeed(@Query("il") city: String, @Query("ilce") district: String): Call<PharmacyResponse>
}