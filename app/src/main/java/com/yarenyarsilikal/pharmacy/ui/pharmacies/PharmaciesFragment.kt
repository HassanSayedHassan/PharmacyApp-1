package com.yarenyarsilikal.pharmacy.ui.pharmacies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yarenyarsilikal.pharmacy.R

class PharmaciesFragment : Fragment() {

    private lateinit var pharmaciesViewModel: PharmaciesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        pharmaciesViewModel = ViewModelProvider(this).get(PharmaciesViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_pharmacies, container, false)
        val textView: TextView = view.findViewById(R.id.text_dashboard)
        pharmaciesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return view
    }
}
