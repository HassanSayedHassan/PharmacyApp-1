package com.yarenyarsilikal.pharmacy.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yarenyarsilikal.pharmacy.R
import com.yarenyarsilikal.pharmacy.network.UrlType
import com.yarenyarsilikal.pharmacy.network.rss.Downloader
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_news, container, false)


        return view
    }

    override fun onStart() {
        super.onStart()
        activity?.let {
            Downloader(
                it,
                UrlType.DONANIMHABER.toString(),
                recyclerViewNews
            ).execute()
        }

    }

}
