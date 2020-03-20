package com.yarenyarsilikal.pharmacy

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.yarenyarsilikal.pharmacy.util.KeyType


class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val url = intent.getStringExtra(KeyType.WebURL.toString())

        val webView: WebView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        webView.loadUrl(url)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_web, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        return if (id == R.id.back) {
            finish()
            true
        } else super.onOptionsItemSelected(item)
    }
}
