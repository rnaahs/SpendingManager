package au.com.techfields.spendingplanner.viewmodel.database

import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.View
import android.view.WindowManager
import au.com.techfields.spendingplanner.model.Category
import au.com.techfields.spendingplanner.model.Transaction
import au.com.techfields.spendingplanner.viewmodel.init.CalendarMonthPickerInit.Companion.dateToCalendar
import au.com.techfields.spendingplanner.viewmodel.item.SummaryCategoryItemAdapter
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

    fun getSummaryCategoryTypeAdapter(summaryTypeArrayList: ArrayList<Category>) = SummaryCategoryItemAdapter(summaryTypeArrayList)

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

        /*
        val transactionList = arrayListOf<Transaction>()
        val calendar1 = Calendar.getInstance()
        val calendar2 = Calendar.getInstance()
        val calendar3 = Calendar.getInstance()
        val calendar4 = Calendar.getInstance()
        val calendar5 = Calendar.getInstance()
        val calendar6 = Calendar.getInstance()
        val calendar7 = Calendar.getInstance()
        val calendar8 = Calendar.getInstance()
        val calendar9 = Calendar.getInstance()
        val calendar10 = Calendar.getInstance()
        val calendar11 = Calendar.getInstance()
        val calendar12 = Calendar.getInstance()
        val calendar13 = Calendar.getInstance()
        val calendar14 = Calendar.getInstance()
        calendar1.set(2019, Calendar.AUGUST, 26, 10, 44, 11)
        calendar2.set(2019, Calendar.AUGUST, 26, 11, 44, 11)
        calendar3.set(2019, Calendar.AUGUST, 27, 10, 44, 11)
        calendar4.set(2019, Calendar.AUGUST, 27, 11, 44, 11)
        calendar5.set(2019, Calendar.AUGUST, 28, 10, 44, 11)
        calendar6.set(2019, Calendar.JULY, 26, 10, 44, 12)
        calendar7.set(2019, Calendar.JUNE, 26, 10, 44, 13)
        calendar8.set(2019, Calendar.JANUARY, 26, 10, 44, 11)
        calendar9.set(2019, Calendar.JANUARY, 26, 11, 44, 11)
        calendar10.set(2019, Calendar.AUGUST, 27, 10, 44, 11)
        calendar11.set(2019, Calendar.AUGUST, 14, 11, 44, 11)
        calendar12.set(2019, Calendar.MARCH, 13, 10, 44, 11)
        calendar13.set(2019, Calendar.APRIL, 12, 11, 44, 12)
        calendar14.set(2019, Calendar.MAY, 11, 10, 44, 13)
        val transactionRecord1 = Transaction("0", "Transaction1", 500.0,  calendar1.time, Transaction.TransactionType.Income.name, "0")
        val transactionRecord2 = Transaction("1", "Transaction2", 830.5, calendar2.time, Transaction.TransactionType.Income.name, "10")
        val transactionRecord3 = Transaction("2", "Transaction3", -700.0, calendar3.time, Transaction.TransactionType.Expense.name, "2")
        val transactionRecord4 = Transaction("3", "Transaction4", -130.5, calendar4.time, Transaction.TransactionType.Expense.name, "5")
        val transactionRecord5 = Transaction("4", "Transaction5", 245.5, calendar5.time, Transaction.TransactionType.Income.name, "0")
        val transactionRecord6 = Transaction("5", "Transaction6", -245.5, calendar6.time, Transaction.TransactionType.Expense.name, "2")
        val transactionRecord7 = Transaction("6", "Transaction7", -70.5, calendar7.time, Transaction.TransactionType.Expense.name, "1")
        val transactionRecord8 = Transaction("7", "Transaction8", 500.0, calendar8.time, Transaction.TransactionType.Income.name, "0")
        val transactionRecord9 = Transaction("8", "Transaction9", -80.5, calendar9.time, Transaction.TransactionType.Expense.name, "3")
        val transactionRecord10 = Transaction("9", "Transaction10", 560.0, calendar10.time, Transaction.TransactionType.Income.name, "0")
        val transactionRecord11 = Transaction("10", "Transaction11", -430.5, calendar11.time, Transaction.TransactionType.Expense.name, "3")
        val transactionRecord12 = Transaction("11", "Transaction12", -445.5, calendar12.time, Transaction.TransactionType.Expense.name, "1")
        val transactionRecord13 = Transaction("12", "Transaction13", -545.5, calendar13.time, Transaction.TransactionType.Expense.name, "4")
        val transactionRecord14 = Transaction("13", "Transaction14", -236.5, calendar14.time, Transaction.TransactionType.Expense.name, "2")
        val transactionRecord15 = Transaction("14", "Transaction15", -80.5, calendar1.time, Transaction.TransactionType.Expense.name, "6")
        val transactionRecord16 = Transaction("15", "Transaction16", 560.0, calendar1.time, Transaction.TransactionType.Income.name, "0")
        val transactionRecord17 = Transaction("16", "Transaction17", -430.5, calendar1.time, Transaction.TransactionType.Expense.name, "7")
        val transactionRecord18 = Transaction("17", "Transaction18", -445.5, calendar1.time, Transaction.TransactionType.Expense.name, "8")
        val transactionRecord19 = Transaction("18", "Transaction19", 545.5, calendar1.time, Transaction.TransactionType.Income.name, "0")
        val transactionRecord20 = Transaction("19", "Transaction20", -236.5, calendar1.time, Transaction.TransactionType.Expense.name, "9")
        val transactionRecord21 = Transaction("20", "Transaction21", 536.5, calendar1.time, Transaction.TransactionType.Income.name, "11")
        val transactionRecord22 = Transaction("21", "Transaction22", 336.5, calendar1.time, Transaction.TransactionType.Income.name, "12")
        val transactionRecord23 = Transaction("22", "Transaction23", 736.5, calendar1.time, Transaction.TransactionType.Income.name, "13")
        val transactionRecord24 = Transaction("23", "Transaction24", 436.5, calendar1.time, Transaction.TransactionType.Income.name, "14")
        transactionList.add(transactionRecord3)
        transactionList.add(transactionRecord1)
        transactionList.add(transactionRecord2)
        transactionList.add(transactionRecord5)
        transactionList.add(transactionRecord4)
        transactionList.add(transactionRecord6)
        transactionList.add(transactionRecord7)
        transactionList.add(transactionRecord8)
        transactionList.add(transactionRecord9)
        transactionList.add(transactionRecord10)
        transactionList.add(transactionRecord11)
        transactionList.add(transactionRecord12)
        transactionList.add(transactionRecord13)
        transactionList.add(transactionRecord14)
        transactionList.add(transactionRecord15)
        transactionList.add(transactionRecord16)
        transactionList.add(transactionRecord17)
        transactionList.add(transactionRecord18)
        transactionList.add(transactionRecord19)
        transactionList.add(transactionRecord20)
        transactionList.add(transactionRecord21)
        transactionList.add(transactionRecord22)
        transactionList.add(transactionRecord23)
        transactionList.add(transactionRecord24)
        addTransactionsToDb()
        */

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