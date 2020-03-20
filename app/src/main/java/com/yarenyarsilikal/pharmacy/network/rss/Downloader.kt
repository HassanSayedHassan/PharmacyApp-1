package com.yarenyarsilikal.pharmacy.network.rss

import android.content.Context
import android.os.AsyncTask
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.yarenyarsilikal.pharmacy.network.rss.Connector.connect
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection


class Downloader(
    var c: Context,
    var urlAddress: String,
    private var rc: RecyclerView,
    private val tv: TextView,
    private val onItemClickListener: (String) -> Unit
) :
    AsyncTask<Void?, Void?, Any>() {
    override fun onPreExecute() {
        super.onPreExecute()

    }


    override fun onPostExecute(data: Any) {
        super.onPostExecute(data)

        if (data.toString().startsWith("Error")) {
            Toast.makeText(c, data.toString(), Toast.LENGTH_SHORT).show()
        } else {
            //PARSE
            RSSParser(c, data as InputStream, rc, tv, onItemClickListener).execute()
        }
    }

    private fun downloadData(): Any {
        val connection =
            connect(urlAddress)
        return if (connection.toString().startsWith("Error")) {
            connection.toString()
        } else try {
            val con = connection as HttpURLConnection
            val responseCode = con.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return BufferedInputStream(con.inputStream)
            }
            ErrorTracker.RESPONSE_ERROR.toString() + con.responseMessage
        } catch (e: IOException) {
            e.printStackTrace()
            ErrorTracker.IO_EROR
        }
    }

    override fun doInBackground(vararg params: Void?): Any {
        return downloadData()
    }

}