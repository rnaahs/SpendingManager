package au.com.techfields.spendingplanner.model

import io.realm.RealmObject
import java.util.Comparator
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Category(@PrimaryKey var mId: String = "",
                    var mName: String = "",
                    var mAmount: Double = 0.0,
                    var mType: String = Transaction.TransactionType.Expense.name,
                    var mIconId: Int = 0,
                    var mColorId: String = ""): RealmObject() {
    companion object SortCategoryAmountByAscendingOrder : Comparator<Category> {
        override fun compare(o1: Category, o2: Category): Int = when {
            o1.mAmount != o2.mAmount -> (o1.mAmount - o2.mAmount).toInt()
            else -> (o2.mAmount - o1.mAmount).toInt()
        }
    }
}