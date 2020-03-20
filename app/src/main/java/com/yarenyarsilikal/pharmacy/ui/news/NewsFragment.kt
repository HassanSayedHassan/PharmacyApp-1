package com.yarenyarsilikal.pharmacy.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yarenyarsilikal.pharmacy.R
import com.yarenyarsilikal.pharmacy.WebViewActivity
import com.yarenyarsilikal.pharmacy.network.UrlType
import com.yarenyarsilikal.pharmacy.network.rss.Downloader
import com.yarenyarsilikal.pharmacy.util.KeyType
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var textViewEmpty: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        textViewEmpty = view.findViewById(R.id.textViewEmpty)
        return view
    }

    override fun onStart() {
        super.onStart()
        activity?.let {
            Downloader(
                it,
                UrlType.DONANIMHABER.toString(),
                recyclerViewNews,
                textViewEmpty
            ) { link ->
                val intent = Intent(activity, WebViewActivity::class.java)
                intent.putExtra(KeyType.WebURL.toString(), link)
                startActivity(intent)
            }.execute()
        }

    }

}
