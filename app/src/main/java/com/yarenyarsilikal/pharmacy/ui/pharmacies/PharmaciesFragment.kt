package com.yarenyarsilikal.pharmacy.ui.pharmacies

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yarenyarsilikal.pharmacy.R
import com.yarenyarsilikal.pharmacy.network.API
import com.yarenyarsilikal.pharmacy.network.Connection
import com.yarenyarsilikal.pharmacy.network.model.PharmacyResponse
import com.yarenyarsilikal.pharmacy.ui.pharmacies.adapter.PharmaciesAdapter
import com.yarenyarsilikal.pharmacy.util.PrefUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PharmaciesFragment : Fragment(), OnSpinerItemClick {

    private lateinit var buttonChoose: Button
    private lateinit var recyclerViewPharmacy: RecyclerView
    private lateinit var cityListFull: HashMap<String, List<String>>
    private lateinit var pickerDialog: PickerDialog
    private lateinit var textViewEmpty: TextView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_pharmacies, container, false)
        initResources()
        initViews(v)
        return v
    }

    private fun initResources() {
        cityListFull = PrefUtil.getCityList()
    }


    private fun initViews(v: View) {
        textViewEmpty = v.findViewById(R.id.textViewEmpty)
        buttonChoose = v.findViewById(R.id.buttonChoose)
        pickerDialog = PickerDialog(activity, cityListFull)
        pickerDialog.bindOnSpinerListener(this)
        buttonChoose.setOnClickListener(View.OnClickListener { v: View? -> pickerDialog.showSpinerDialog() })
        recyclerViewPharmacy = v.findViewById(R.id.recyclerViewPharmacy)

    }


    override fun onItemClick(city: String, district: String) {
        pickerDialog.closeSpinerDialog()
        val connection = Connection()
        connection.getClient()!!.create(API::class.java).getFeed(city, district)
            .enqueue(object : Callback<PharmacyResponse> {
                override fun onResponse(
                    call: Call<PharmacyResponse>,
                    response: Response<PharmacyResponse>
                ) {
                    if (response.isSuccessful) {
                        val list = response.body()?.result
                        if (list != null && list.isNotEmpty()) {
                            recyclerViewPharmacy.visibility = View.VISIBLE
                            recyclerViewPharmacy.adapter =
                                list?.let { PharmaciesAdapter(it, onMapClick(), onPhoneClick()) }
                            recyclerViewPharmacy.layoutManager = LinearLayoutManager(activity)
                        } else {
                            recyclerViewPharmacy.visibility = View.GONE
                            textViewEmpty.visibility = View.VISIBLE
                            textViewEmpty.text = getString(R.string.pharmacy_empty)
                        }

                    } else
                        Toast.makeText(
                            activity,
                            "İşlem başarısız oldu tekrar deneyiniz!-1",
                            Toast.LENGTH_LONG
                        ).show()

                }

                override fun onFailure(call: Call<PharmacyResponse>, t: Throwable) {
                    Toast.makeText(
                        activity,
                        "İşlem başarısız oldu tekrar deneyiniz!-2",
                        Toast.LENGTH_LONG
                    ).show()

                }

            })
    }


    fun onMapClick() = { loc: String ->

        val uri = "geo:$loc"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        context!!.startActivity(intent)
    }

    fun onPhoneClick() = { number: String ->
        val i = Intent(Intent.ACTION_CALL)
        i.data = Uri.parse("tel:+9$number")
        startActivity(i)

    }


}