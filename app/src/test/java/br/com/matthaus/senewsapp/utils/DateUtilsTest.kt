package br.com.matthaus.senewsapp.utils

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*

class DateUtilsTest {

    @Test
    fun `should formatter parse correctly date to string version`() {
        val calendar = GregorianCalendar(2019, Calendar.APRIL, 1, 1, 14).let { it.time }
        val result = DateUtils.formatDatetoString(calendar)

        assertEquals("01 April 2019 - 01:14", result)
    }

}