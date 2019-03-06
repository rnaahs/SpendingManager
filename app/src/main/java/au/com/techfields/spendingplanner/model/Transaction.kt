package au.com.techfields.spendingplanner.model

import java.util.*

data class Transaction(var mId: String,
                       var mName: String,
                       var mAmount: Double,
                       var mCalendar: Calendar,
                       var mType: TransactionType,
                       var mCategoryId: String) {enum class TransactionType {Income, Expense} }