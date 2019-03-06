package au.com.techfields.spendingplanner.viewmodel

import au.com.techfields.spendingplanner.model.Transaction
import java.util.*

class SortTransactionByDate {
    companion object : Comparator<Transaction> {
        override fun compare(a: Transaction, b: Transaction): Int = when {
            a.mCalendar.get(Calendar.YEAR) != b.mCalendar.get(Calendar.YEAR) -> b.mCalendar.get(Calendar.YEAR) - a.mCalendar.get(Calendar.YEAR)
            a.mCalendar.get(Calendar.MONTH) != b.mCalendar.get(Calendar.MONTH) -> b.mCalendar.get(Calendar.MONTH) - a.mCalendar.get(Calendar.MONTH)
            a.mCalendar.get(Calendar.DATE) != b.mCalendar.get(Calendar.DATE) -> b.mCalendar.get(Calendar.DATE) - a.mCalendar.get(Calendar.DATE)
            a.mCalendar.get(Calendar.HOUR) != b.mCalendar.get(Calendar.HOUR) -> b.mCalendar.get(Calendar.HOUR) - a.mCalendar.get(Calendar.HOUR)
            a.mCalendar.get(Calendar.MINUTE) != b.mCalendar.get(Calendar.MINUTE) -> b.mCalendar.get(Calendar.MINUTE) - a.mCalendar.get(Calendar.MINUTE)
            else -> b.mCalendar.get(Calendar.SECOND) - a.mCalendar.get(Calendar.SECOND)
        }
    }
}