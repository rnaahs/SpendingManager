package au.com.techfields.spendingplanner.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import au.com.techfields.spendingplanner.R
import au.com.techfields.spendingplanner.model.Transaction
import au.com.techfields.spendingplanner.viewmodel.TransactionComponentAdapter
import kotlinx.android.synthetic.main.fragment_transaction_main.view.*
import java.util.*
import au.com.techfields.spendingplanner.viewmodel.SortTransactionByDate


class TransactionMainFragment : Fragment() {
    lateinit var mTransactionComponentRv: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val componentView = inflater.inflate(R.layout.fragment_transaction_main, container, false)
        val calendar1 = Calendar.getInstance()
        val calendar2= Calendar.getInstance()
        val calendar3 = Calendar.getInstance()
        val calendar4 = Calendar.getInstance()
        val calendar5 = Calendar.getInstance()
        calendar1.set(2019, 1, 26, 10, 44, 11)
        calendar2.set(2019, 1, 26, 11, 44, 11)
        calendar3.set(2019, 1, 27, 10, 44, 11)
        calendar4.set(2019, 1, 27, 11, 44, 11)
        calendar5.set(2019, 1, 28, 10, 44, 11)
        val transactionRecord1 = Transaction("0", "Transaction1", 500.0, calendar1, Transaction.TransactionType.Income, "11")
        val transactionRecord2 = Transaction("1", "Transaction2", -30.5, calendar2, Transaction.TransactionType.Expense, "12")
        val transactionRecord3 = Transaction("2", "Transaction3", 700.0, calendar3, Transaction.TransactionType.Income, "11")
        val transactionRecord4 = Transaction("3", "Transaction4", -130.5, calendar4, Transaction.TransactionType.Expense, "12")
        val transactionRecord5 = Transaction("4", "Transaction5", 245.5, calendar5, Transaction.TransactionType.Income, "12")
        val transactionArrayList = arrayListOf<Transaction>()
        transactionArrayList.add(transactionRecord3)
        transactionArrayList.add(transactionRecord1)
        transactionArrayList.add(transactionRecord2)
        transactionArrayList.add(transactionRecord5)
        transactionArrayList.add(transactionRecord4)
        //sort by date
        val sortedTransactionList = transactionArrayList.sortedWith(SortTransactionByDate)
        val transactionComponentArrayList = arrayListOf<ArrayList<Transaction>>()
        var day = 0
        var transactionItemArrayList = arrayListOf<Transaction>()
        for(transaction in sortedTransactionList) {
            if(day == 0) {
                day = transaction.mCalendar.get(Calendar.DATE)
            }
            else if(day != 0 && day != transaction.mCalendar.get(Calendar.DATE)) {
                transactionComponentArrayList.add(transactionItemArrayList)
                transactionItemArrayList = arrayListOf()
                day = transaction.mCalendar.get(Calendar.DATE)
            }
            transactionItemArrayList.add(transaction)
        }
        if(transactionItemArrayList.isNotEmpty()) transactionComponentArrayList.add(transactionItemArrayList)

        mTransactionComponentRv = componentView.findViewById(R.id.transaction_rv)
        mTransactionComponentRv.layoutManager = LinearLayoutManager(componentView.context)
        mTransactionComponentRv.adapter = TransactionComponentAdapter(transactionComponentArrayList, componentView.context)

        componentView.add_transaction_btn.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        return componentView
    }
}
