package au.com.techfields.spendingplanner.viewmodel

import au.com.techfields.spendingplanner.model.Category
import au.com.techfields.spendingplanner.model.Transaction
import java.util.*
import kotlin.collections.ArrayList

class DatabaseAdapter {
    private val mTransactionArrayList = arrayListOf<Transaction>()
    val mTransactionComponentArrayList = arrayListOf<ArrayList<Transaction>>()
    val mSummaryExpenseArrayList = arrayListOf<Category>()
    val mSummaryIncomeArrayList = arrayListOf<Category>()

    init {
        addTransactionsFromDB()
        addCategoriesFromDB()
    }

    companion object {
        val mDatabaseAdapter: DatabaseAdapter = DatabaseAdapter()
    }

    fun getSummaryTypeAdapter(summaryTypeArrayList: ArrayList<Category>) =  SummaryTypeAdapter(summaryTypeArrayList)

    fun getTotalAmount(vararg totalAmountArrayLists: ArrayList<Category>): Double {
        var totalAmount = 0.0
        for(index in totalAmountArrayLists.indices) {
            for(category in totalAmountArrayLists[index]) {
                totalAmount += category.mAmount
            }
        }
        return totalAmount
    }

    private fun addTransactionsFromDB() {
        val calendar1 = Calendar.getInstance()
        val calendar2= Calendar.getInstance()
        val calendar3 = Calendar.getInstance()
        val calendar4 = Calendar.getInstance()
        val calendar5 = Calendar.getInstance()
        val calendar6 = Calendar.getInstance()
        val calendar7 = Calendar.getInstance()
        calendar1.set(2019, 1, 26, 10, 44, 11)
        calendar2.set(2019, 1, 26, 11, 44, 11)
        calendar3.set(2019, 1, 27, 10, 44, 11)
        calendar4.set(2019, 1, 27, 11, 44, 11)
        calendar5.set(2019, 1, 28, 10, 44, 11)
        calendar6.set(2019, 1, 26, 10, 44, 12)
        calendar7.set(2019, 1, 26, 10, 44, 13)
        val transactionRecord1 = Transaction("0", "Transaction1", 500.0, calendar1, Transaction.TransactionType.Income, "0")
        val transactionRecord2 = Transaction("1", "Transaction2", -30.5, calendar2, Transaction.TransactionType.Expense, "1")
        val transactionRecord3 = Transaction("2", "Transaction3", 700.0, calendar3, Transaction.TransactionType.Income, "0")
        val transactionRecord4 = Transaction("3", "Transaction4", -130.5, calendar4, Transaction.TransactionType.Expense, "1")
        val transactionRecord5 = Transaction("4", "Transaction5", 245.5, calendar5, Transaction.TransactionType.Income, "0")
        val transactionRecord6 = Transaction("5", "Transaction6", 245.5, calendar6, Transaction.TransactionType.Income, "0")
        val transactionRecord7 = Transaction("6", "Transaction7", -26.5, calendar7, Transaction.TransactionType.Expense, "1")
        mTransactionArrayList.add(transactionRecord3)
        mTransactionArrayList.add(transactionRecord1)
        mTransactionArrayList.add(transactionRecord2)
        mTransactionArrayList.add(transactionRecord5)
        mTransactionArrayList.add(transactionRecord4)
        mTransactionArrayList.add(transactionRecord6)
        mTransactionArrayList.add(transactionRecord7)
        sortTransactionComponentsByDate()
    }
/*Invoke when adding categories from DB
* Add the total number of categories to Category ArrayList which is either income or expense*/
    private fun addCategoriesFromDB() {
        val categoryRecord1 = Category("0", "Category1", 1.0, "Income", 123, "156")
        val categoryRecord2 = Category("1", "Category2", -187.5, "Expense", 125, "157")
    val categoryRecord3 = Category("0", "Category3", 1.0, "Income", 123, "156")
    val categoryRecord4 = Category("1", "Category4", -187.5, "Expense", 125, "157")
    val categoryRecord5 = Category("0", "Category5", 1.0, "Income", 123, "156")
    val categoryRecord6 = Category("1", "Category6", -187.5, "Expense", 125, "157")
    val categoryRecord7 = Category("0", "Category7", 1.0, "Income", 123, "156")
    val categoryRecord8 = Category("1", "Category8", -187.5, "Expense", 125, "157")
    val categoryRecord9 = Category("0", "Category9", 1.0, "Income", 123, "156")
    val categoryRecord10 = Category("1", "Category10", -187.5, "Expense", 125, "157")
    val categoryRecord11= Category("0", "Category11", 1.0, "Income", 123, "156")
    val categoryRecord12 = Category("1", "Category12", -187.5, "Expense", 125, "157")
        mSummaryIncomeArrayList.add(categoryRecord1)
        mSummaryExpenseArrayList.add(categoryRecord2)
    mSummaryIncomeArrayList.add(categoryRecord3)
    mSummaryExpenseArrayList.add(categoryRecord4)
    mSummaryIncomeArrayList.add(categoryRecord5)
    mSummaryExpenseArrayList.add(categoryRecord6)
    mSummaryIncomeArrayList.add(categoryRecord7)
    mSummaryExpenseArrayList.add(categoryRecord8)
    mSummaryIncomeArrayList.add(categoryRecord9)
    mSummaryExpenseArrayList.add(categoryRecord10)
    mSummaryIncomeArrayList.add(categoryRecord11)
    mSummaryExpenseArrayList.add(categoryRecord12)
    }
    /*Invoke when adding transaction to DB */
    fun setTransactionToDB() {

    }
    /*Invoke when adding new category to DB*/
    fun setCategoryToDB() {

    }

    //TODO: sortByDate method must take account of weekly, mothly, and yearly with parameters given
    private fun sortTransactionComponentsByDate() {
        var day = 0
        var transactionItemArrayList = arrayListOf<Transaction>()
        //sort by date
        val sortedTransactionList = mTransactionArrayList.sortedWith(Transaction.SortByDateTransactionComparator)
        for(transaction in sortedTransactionList) {
            if(day == 0) {
                day = transaction.mCalendar.get(Calendar.DATE)
            }
            else if(day != 0 && day != transaction.mCalendar.get(Calendar.DATE)) {
                mTransactionComponentArrayList.add(transactionItemArrayList)
                transactionItemArrayList = arrayListOf()
                day = transaction.mCalendar.get(Calendar.DATE)
            }
            transactionItemArrayList.add(transaction)
        }
        if(transactionItemArrayList.isNotEmpty()) mTransactionComponentArrayList.add(transactionItemArrayList)
    }
}