package au.com .techfields.spendingplanner.model

import java.util.Comparator

data class Category(var mId:String,
                    var mName: String,
                    var mAmount: Double,
                    var mType: String,
                    var mIconId: Int,
                    var mColorId: String) {
    //TODO: fix sortByAmount Category
/*    companion object SortByAmountCategoryComparator: Comparator<Category> {
        override fun compare(o1: Category, o2: Category): Int = when {
             o1.mAmount != o2.mAmount -> (Double to o1.mAmount - o2.mAmount) as Int
             else -> (Double to o2.mAmount - o1.mAmount) as Int
        }
    }*/
}