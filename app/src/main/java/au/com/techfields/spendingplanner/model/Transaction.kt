package au.com.techfields.spendingplanner.model

import java.util.*

data class Transaction(var mId: String,
                       var mName: String,
                       var mAmount: Double,
                       var mDate: Date,
                       var mType: String,
                       var mCategoryId: String)