package com.yarenyarsilikal.pharmacy.util

import android.os.Build
import android.widget.TextView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


infix fun TextView.setDateToTextView(date: String?) {
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val formatter = DateTimeFormatter.ofPattern("MM dd")
        LocalDate.parse(date, formatter).toString()
    } else {
        val parser = SimpleDateFormat("MM dd", Locale.getDefault())
        parser.format(date).toString()
    }
}