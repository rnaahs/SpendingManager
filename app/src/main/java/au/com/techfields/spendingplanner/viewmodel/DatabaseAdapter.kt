package au.com.techfields.spendingplanner.viewmodel

import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.View
import android.view.WindowManager
import au.com.techfields.spendingplanner.model.Category
import au.com.techfields.spendingplanner.model.Transaction
import au.com.techfields.spendingplanner.viewmodel.MonthPickerInit.Companion.dateToCalendar
import io.realm.ImportFlag
import io.realm.Realm
import io.realm.kotlin.where
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class DatabaseAdapter {
    private var mTransactionArrayList = getTransactionsFromDb()
    private val mCategoryArrayList = arrayListOf<Category>()
    val mTransactionComponentArrayList = arrayListOf<ArrayList<Transaction>>()
    val mSummaryExpenseArrayList = arrayListOf<Category>()
    val mSummaryIncomeArrayList = arrayListOf<Category>()
    var totalIncomeAmount = getTotalAmount(mSummaryIncomeArrayList)
    var totalExpenseAmount = getTotalAmount(mSummaryExpenseArrayList)
    var totalAmount = getTotalAmount(mSummaryIncomeArrayList, mSummaryExpenseArrayList)

    init {
        sortTransactionComponentsByDate(Calendar.getInstance())
        sortCategoriesByDate()
    }

    companion object {
        val mDatabaseAdapter: DatabaseAdapter = DatabaseAdapter()
    }

    fun getSummaryCategoryTypeAdapter(summaryTypeArrayList: ArrayList<Category>) = SummaryCategoryTypeAdapter(summaryTypeArrayList)

    fun getTotalAmount(vararg totalAmountArrayLists: ArrayList<Category>): Double {
        var totalAmount = 0.0
        for (index in totalAmountArrayLists.indices) {
            for (category in totalAmountArrayLists[index]) {
                totalAmount += category.mAmount
            }
        }
        return totalAmount
    }

    fun addTransactionsToDb() {
        val realmInstance = Realm.getDefaultInstance()
        realmInstance.executeTransactionAsync({ realm ->
            realm.copyToRealm(mTransactionArrayList, ImportFlag.CHECK_SAME_VALUES_BEFORE_SET)
        }, {
            Log.i("Success", "Data inserted to Realm DB")
        }, {
            Log.e("Fail", it.message, Exception(it.cause))
        })
        realmInstance.close()
    }

    fun getTransactionsFromDb(): ArrayList<Transaction> {
        val realmInstance = Realm.getDefaultInstance()
        val realmResultOfTransaction = realmInstance.where<Transaction>().findAll()
        val transactionList = arrayListOf<Transaction>()
        transactionList.addAll(realmInstance.copyFromRealm(realmResultOfTransaction))
        realmInstance.close()
        return transactionList
    }

    fun updateTransactionsFromDb() {
        val realmInstance = Realm.getDefaultInstance()
        val realmResultOfTransaction = realmInstance.where<Transaction>().findAll()
        mTransactionArrayList.clear()
        mTransactionArrayList.addAll(realmInstance.copyFromRealm(realmResultOfTransaction))
        realmInstance.close()
    }

    fun sortCategoriesByDate() {
        mSummaryIncomeArrayList.clear()
        mSummaryExpenseArrayList.clear()
        mCategoryArrayList.clear()
        var isCategoryMatched = false
        for (transactionArrayList in mTransactionComponentArrayList) {
            for(transaction in transactionArrayList) {
                if(mCategoryArrayList.size == 0) {
                    mCategoryArrayList.add(Category(transaction.mCategoryId, "Category${transaction.mCategoryId.toInt()+1}", transaction.mAmount, transaction.mType, 123, "156"))
                } else {
                    for(category in mCategoryArrayList) {
                        if(transaction.mCategoryId == category.mId) {
                            category.mAmount += transaction.mAmount
                            isCategoryMatched = true
                        }
                    }
                    if(!isCategoryMatched) mCategoryArrayList.add(Category(transaction.mCategoryId, "Category${transaction.mCategoryId.toInt()+1}", transaction.mAmount, transaction.mType, 123, "156"))
                    isCategoryMatched = false
                }
            }
        }
        setCategoryTypeArrayList()
    }

    private fun setCategoryTypeArrayList() {
        val categorySortedByDateList = mCategoryArrayList.sortedWith(Category.SortCategoryAmountByAscendingOrder)
        for (category in categorySortedByDateList) {
            if (category.mType == Transaction.TransactionType.Income.name)
                mSummaryIncomeArrayList.add(0, category)
            else if (category.mType == Transaction.TransactionType.Expense.name)
                mSummaryExpenseArrayList.add(category)
        }
    }

    fun sortTransactionComponentsByDate(calendar: Calendar) {
        var day = 0
        mTransactionComponentArrayList.clear()
        var transactionItemArrayList = arrayListOf<Transaction>()
        val transactionSortedByMonthYearList = sortTransactionsByMonthYear(mTransactionArrayList, calendar)
        val transactionSortedByDateList = transactionSortedByMonthYearList.sortedWith(Transaction.SortByDateTransactionComparator)
        for (transaction in transactionSortedByDateList) {
            if (day == 0) {
                day = dateToCalendar(transaction.mDate).get(Calendar.DATE)
            } else if (day != 0 && day != dateToCalendar(transaction.mDate).get(Calendar.DATE)) {
                mTransactionComponentArrayList.add(transactionItemArrayList)
                transactionItemArrayList = arrayListOf()
                day = dateToCalendar(transaction.mDate).get(Calendar.DATE)
            }

            transactionItemArrayList.add(transaction)
        }
        if (transactionItemArrayList.isNotEmpty()) mTransactionComponentArrayList.add(transactionItemArrayList)
    }


    private fun sortTransactionsByMonthYear(transactionSortedList: ArrayList<Transaction>, calendar: Calendar): ArrayList<Transaction> {
        val sortTransactionListByMonth = arrayListOf<Transaction>()
        for (transaction in transactionSortedList) {

            if (calendar.get(Calendar.YEAR) == dateToCalendar(transaction.mDate).get(Calendar.YEAR) &&
                    calendar.get(Calendar.MONTH) == dateToCalendar(transaction.mDate).get(Calendar.MONTH)) {
                sortTransactionListByMonth.add(transaction)
            }
        }
        return sortTransactionListByMonth
    }

    fun getResizedHeight(height: Int, view: View): Int = (setPointToResize(view).y * height * 0.01).toInt()
    fun getResizedWidth(width: Int, view: View): Int = (setPointToResize(view).x * width * 0.01).toInt()

    private fun setPointToResize(view: View): Point {
        val wm = view.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        wm.defaultDisplay.getRealSize(point)
        return point
    }
}