package com.example.noteapp.util


import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: Date): String {
    val format = SimpleDateFormat("EEE d MMM @h:mm a",
        Locale.getDefault())
    return format.format(date)
}