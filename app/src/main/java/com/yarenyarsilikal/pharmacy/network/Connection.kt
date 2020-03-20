package com.yarenyarsilikal.pharmacy.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Connection {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            val logging = HttpLoggingInterceptor()
            val header = HeaderInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(header)
                .build()
            retrofit = Retrofit.Builder().baseUrl(UrlType.COLLECTAPI.toString())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build()
        }
        return retrofit
    }
}