package com.yarenyarsilikal.pharmacy.util

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


infix fun <T : AppCompatActivity> AppCompatActivity.extStartActivity(className: Class<T>) {
    startActivity(Intent(this, className))
}

infix fun Context.extToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}


