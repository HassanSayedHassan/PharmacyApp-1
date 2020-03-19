package com.yarenyarsilikal.pharmacy.network.rss

import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object Connector {
    fun connect(urlAddress: String?): Any {
        return try {
            val url = URL(urlAddress)
            val con = url.openConnection() as HttpURLConnection
            con.requestMethod = "GET"
            con.connectTimeout = 15000
            con.readTimeout = 15000
            con.doInput = true
            con
        } catch (e: MalformedURLException) {
            e.printStackTrace()

            ErrorTracker.WRONG_URL_FORMAT
        } catch (e: IOException) {
            e.printStackTrace()
            ErrorTracker.CONNECTION_ERROR
        }
    }
}
