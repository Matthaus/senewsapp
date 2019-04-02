package br.com.matthaus.senewsapp.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {

        private val formatter = SimpleDateFormat("dd MMMM yyyy - HH:mm")

        fun formatDatetoString(date: Date) = formatter.format(date)
    }

}