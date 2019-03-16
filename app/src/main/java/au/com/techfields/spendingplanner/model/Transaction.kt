package au.com.techfields.spendingplanner.model

import java.util.*

data class Transaction(var mId: String,
                       var mName: String,
                       var mAmount: Double,
                       var mCalendar: Calendar,
                       var mType: TransactionType,
                       var mCategoryId: String) {
    enum class TransactionType {Income, Expense}
    companion object SortByDateTransactionComparator : Comparator<Transaction> {
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
