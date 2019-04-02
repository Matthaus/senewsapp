package br.com.matthaus.senewsapp.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {

        private val DEFAULT_DATE_PATTERN = "dd MMMM yyyy - HH:mm"

        private val formatter = SimpleDateFormat(DEFAULT_DATE_PATTERN, Locale.US)

        fun formatDatetoString(date: Date) = formatter.format(date)
    }

}