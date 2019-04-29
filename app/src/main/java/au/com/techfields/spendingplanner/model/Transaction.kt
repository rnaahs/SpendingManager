package au.com.techfields.spendingplanner.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class Transaction(@PrimaryKey var mId: String = "",
                       var mName: String = "",
                       var mAmount: Double = 0.0,
                       var mDate: Date = Date(),
                       var mType: String = TransactionType.Expense.name,
                       var mCategoryId: String = ""): RealmObject() {
    enum class TransactionType {Income, Expense}
    companion object SortByDateTransactionComparator: Comparator<Transaction> {
        override fun compare(a: Transaction, b: Transaction): Int {
            val aCalendar = Calendar.getInstance()
            val bCalendar = Calendar.getInstance()
            aCalendar.time = a.mDate
            bCalendar.time = b.mDate
            return when {
                aCalendar.get(Calendar.YEAR) != bCalendar.get(Calendar.YEAR) -> bCalendar.get(Calendar.YEAR) - aCalendar.get(Calendar.YEAR)
                aCalendar.get(Calendar.MONTH) != bCalendar.get(Calendar.MONTH) -> bCalendar.get(Calendar.MONTH) - aCalendar.get(Calendar.MONTH)
                aCalendar.get(Calendar.DATE) != bCalendar.get(Calendar.DATE) -> bCalendar.get(Calendar.DATE) - aCalendar.get(Calendar.DATE)
                aCalendar.get(Calendar.HOUR) != bCalendar.get(Calendar.HOUR) -> bCalendar.get(Calendar.HOUR) - aCalendar.get(Calendar.HOUR)
                aCalendar.get(Calendar.MINUTE) != bCalendar.get(Calendar.MINUTE) -> bCalendar.get(Calendar.MINUTE) - aCalendar.get(Calendar.MINUTE)
                else -> bCalendar.get(Calendar.SECOND) - aCalendar.get(Calendar.SECOND)
            }
        }
    }

}



