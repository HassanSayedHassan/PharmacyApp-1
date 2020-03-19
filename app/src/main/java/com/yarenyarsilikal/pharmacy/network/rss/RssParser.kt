package com.yarenyarsilikal.pharmacy.network.rss

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yarenyarsilikal.pharmacy.network.model.RssItem
import com.yarenyarsilikal.pharmacy.ui.news.adapter.NewsAdapter
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream
import java.util.*


class RSSParser(
    private var c: Context,
    private var `is`: InputStream,
    private var rc: RecyclerView
) :
    AsyncTask<Void?, Void?, Boolean>() {
    private var pd: ProgressDialog? = null
    private var items: ArrayList<RssItem> = ArrayList<RssItem>()
    override fun onPreExecute() {
        super.onPreExecute()
        pd = ProgressDialog(c)
        pd!!.setTitle("Parse data")
        pd!!.setMessage("Parsing Data...Please wait")
        pd!!.show()
    }


    override fun onPostExecute(isParsed: Boolean) {
        super.onPostExecute(isParsed)
        pd!!.dismiss()
        if (isParsed) {
            //BIND

            rc.adapter = NewsAdapter(items)
            rc.layoutManager = LinearLayoutManager(c)
            // lv.adapter = CustomAdapter(c, articles)
        } else {
            Toast.makeText(c, "Unable To Parse", Toast.LENGTH_SHORT).show()
        }
    }

    private fun parseRSS(): Boolean {
        try {
            val factory =
                XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            parser.setInput(`is`, null)
            var event = parser.eventType
            var value: String? = null
            items.clear()
            var article = RssItem()
            do {
                val name = parser.name
                when (event) {
                    XmlPullParser.START_TAG -> if (name == "item") {
                        article = RssItem()
                    }
                    XmlPullParser.TEXT -> value = parser.text
                    XmlPullParser.END_TAG -> {
                        when (name) {
                            "title" -> {
                                article.title = value
                            }
                            "description" -> {
                                article.description = value
                            }
                            "pubDate" -> {
                                article.date = value
                            }
                            "link" -> {
                                article.link = value
                            }
                        }
                        if (name == "item") {
                            items.add(article)
                        }
                    }
                }
                event = parser.next()
            } while (event != XmlPullParser.END_DOCUMENT)
            return true
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return false
    }

    override fun doInBackground(vararg params: Void?): Boolean {
        return parseRSS()
    }

}

