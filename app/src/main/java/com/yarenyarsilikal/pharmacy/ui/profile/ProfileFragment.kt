package com.yarenyarsilikal.pharmacy.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.yarenyarsilikal.pharmacy.R
import com.yarenyarsilikal.pharmacy.network.model.User
import com.yarenyarsilikal.pharmacy.util.PrefUtil


class ProfileFragment : Fragment(), View.OnClickListener {
    private lateinit var mListener: OnFragmentInteractionListener

    private lateinit var editTextName: EditText
    private lateinit var editTextSurname: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var textViewName: TextView
    private lateinit var textViewSurname: TextView
    private lateinit var textViewEmail: TextView
    private lateinit var imageViewPicture: ImageView
    private lateinit var buttonEdit: Button
    private lateinit var imageButtonEdit: ImageButton


    private lateinit var user: User
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        user = PrefUtil.getUser()
        initView(view)
        return view
    }

    private fun initView(v: View) {
        editTextName = v.findViewById(R.id.editTextName)
        editTextSurname = v.findViewById(R.id.editTextSurname)
        editTextEmail = v.findViewById(R.id.editTextEmail)
        textViewName = v.findViewById(R.id.textViewName)
        textViewSurname = v.findViewById(R.id.textViewSurname)
        textViewEmail = v.findViewById(R.id.textViewEmail)
        imageViewPicture = v.findViewById(R.id.imageViewPicture)
        buttonEdit = v.findViewById(R.id.buttonEdit)
        imageButtonEdit = v.findViewById(R.id.imageButtonEdit)
        fillTextViews()
        buttonEdit.setOnClickListener { editProfile() }
    }

    override fun onClick(v: View?) {

    }

    private fun editProfile() {
        if (buttonEdit.text != getString(R.string.done)) {
            setVisibility(View.VISIBLE)
            buttonEdit.text = getString(R.string.done)
        } else {
            var name: String? = ""
            var surname: String? = ""
            var email: String? = ""

            if (editTextName.text.toString().isEmpty()) {
                editTextName.error = "Lütfen isim giriniz"
            } else {
                name = editTextName.text.toString()
            }

            if (editTextSurname.text.toString().isEmpty()) {
                editTextSurname.error = "Lütfen soyisim giriniz"
            } else {
                surname = editTextSurname.text.toString()
            }

            if (editTextEmail.text.toString().isEmpty()) {
                editTextEmail.error = "Lütfen email giriniz"
            } else {
                email = editTextEmail.text.toString()
            }

            if ((name?.length != 0 && surname?.length != 0 && email?.length != 0) &&
                (name != null && surname != null && email != null)
            ) {

                val user = User(name, surname, email)
                PrefUtil.setUser(user)
                buttonEdit.text = getString(R.string.edit)
                fillTextViews()
                setVisibility(View.GONE)

                mListener.onFragmentInteraction("Değişiklikleriniz Kaydedildi!")
            } else {
                checkIsEmpty("Lütfen isim giriniz", editTextName)
                checkIsEmpty("Lütfen soyisim giriniz", editTextSurname)
                checkIsEmpty("Lütfen email giriniz", editTextEmail)
            }

        }

    }

    private fun checkIsEmpty(value: String, et: EditText) {
        if (et.text.toString().isEmpty()) {
            et.error = value
        }
    }

    private fun setVisibility(int: Int) {
        editTextName.visibility = int
        editTextSurname.visibility = int
        editTextEmail.visibility = int
    }

    private fun fillTextViews() {
        textViewName.text = user.name
        textViewSurname.text = user.surname
        textViewEmail.text = user.email
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context as OnFragmentInteractionListener
        } else {
            throw RuntimeException(
                context.toString() + " must implement OnFragmentInteractionListener"
            )
        }
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(msg: String)
    }
}
