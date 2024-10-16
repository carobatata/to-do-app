package com.example.todoapp

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

fun transformTimestampToDateString(time: Timestamp) :String{
    val date = time.toDate()
    val formatDesired = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
    return formatDesired.format(date)
}