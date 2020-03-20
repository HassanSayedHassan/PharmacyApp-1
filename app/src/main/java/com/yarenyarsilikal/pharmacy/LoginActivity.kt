package com.yarenyarsilikal.pharmacy

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.yarenyarsilikal.pharmacy.network.model.User
import com.yarenyarsilikal.pharmacy.util.PrefUtil
import com.yarenyarsilikal.pharmacy.util.extStartActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {

        login.setOnClickListener {
            val email: String? = editTextEmail.text.toString()
            val password: String? = editTextPassword.text.toString()
            if ((PrefUtil.getUser().email != null || email == PrefUtil.getUser().email) && email?.length != 0) {
                if (PrefUtil.getUser().password == password && password?.length != 0) {
                    logIn()
                } else
                    editTextPassword.error = "Yanlış Şifre Girdiniz!"

            } else {
                editTextEmail.error = "Yanlış Email Girdiniz!"
            }
        }
        register.setOnClickListener {
            var name: String? = ""
            var surname: String? = ""
            var email: String? = ""
            var password: String? = ""

            if (login.visibility == View.VISIBLE) {
                editTextName.visibility = View.VISIBLE
                editTextSurname.visibility = View.VISIBLE
                editTextEmail.setText("")
                editTextPassword.setText("")
                login.visibility = View.INVISIBLE
            } else {
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

                if (editTextPassword.text.toString().isEmpty()) {
                    editTextPassword.error = "Lütfen parola giriniz"
                } else {
                    password = editTextPassword.text.toString()
                }


                if ((name?.length != 0 && surname?.length != 0 && email?.length != 0 && password?.length != 0) &&
                    (name != null && surname != null && email != null && password != null)
                ) {

                    val user = User(name, surname, email, password)
                    PrefUtil.setUser(user)
                    logIn()
                } else {
                    checkIsEmpty("Lütfen isim giriniz", editTextName)
                    checkIsEmpty("Lütfen soyisim giriniz", editTextSurname)
                    checkIsEmpty("Lütfen email giriniz", editTextEmail)
                    checkIsEmpty("Lütfen parola giriniz", editTextPassword)
                }
            }
        }
    }

    private fun checkIsEmpty(value: String, et: EditText) {
        if (et.text.toString().isEmpty()) {
            et.error = value
        }
    }

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    private fun logIn() {
        PrefUtil.setFirstTime(false)
        this@LoginActivity extStartActivity MainActivity::class.java
        finish()

    }
}
