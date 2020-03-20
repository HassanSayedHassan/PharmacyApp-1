package com.yarenyarsilikal.pharmacy.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("authorization", "apikey 34wcyEhPOhTFf4Hr3vqCur:6x41G6ZX3LGOUl2Z6qv3sK")
                .addHeader("Content-Type", "application/json")
                .build()
        )
    }
}