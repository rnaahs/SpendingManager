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

class TransactionMainFragment : Fragment() {
    lateinit var mTransactionRv:RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val componentView = inflater.inflate(R.layout.fragment_transaction_main, container, false)
        val transactionRecord1 = Transaction("0", "Transaction1", 500.0, Date(), "Budget", "11")
        val transactionRecord2 = Transaction("1", "Transaction2", -30.5, Date(), "Expenditure", "12")
        val transactionRecord3 = Transaction("0", "Transaction3", 700.0, Date(), "Budget", "11")
        val transactionRecord4 = Transaction("1", "Transaction4", -130.5, Date(), "Expenditure", "12")
        val transactionArray1 = arrayListOf<Transaction>()
        val transactionArray2 = arrayListOf<Transaction>()
        transactionArray1.add(transactionRecord1)
        transactionArray1.add(transactionRecord2)
        transactionArray2.add(transactionRecord3)
        transactionArray2.add(transactionRecord4)

        val transactionArrayList = arrayListOf(arrayListOf<Transaction>())
        transactionArrayList.add(transactionArray1)
        transactionArrayList.add(transactionArray2)
        mTransactionRv = componentView.findViewById(R.id.transaction_rv)
        mTransactionRv.layoutManager = LinearLayoutManager(componentView.context)
        mTransactionRv.adapter = TransactionComponentAdapter(transactionArrayList)

        componentView.add_transaction_btn.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        return componentView
    }
}
