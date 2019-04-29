package au.com.techfields.spendingplanner.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import au.com.techfields.spendingplanner.R
import au.com.techfields.spendingplanner.viewmodel.DatabaseAdapter
import au.com.techfields.spendingplanner.viewmodel.TransactionComponentAdapter
import kotlinx.android.synthetic.main.fragment_transaction_main.view.*


class TransactionMainFragment : Fragment() {
    lateinit var mTransactionComponentRv: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val componentView = inflater.inflate(R.layout.fragment_transaction_main, container, false)
        mTransactionComponentRv = componentView.findViewById(R.id.transaction_rv)
        mTransactionComponentRv.layoutManager = LinearLayoutManager(componentView.context)
        mTransactionComponentRv.adapter = TransactionComponentAdapter(DatabaseAdapter.mDatabaseAdapter.mTransactionComponentArrayList, componentView.context)
        componentView.add_transaction_btn.setOnClickListener {
            val intent = Intent(componentView.context, AddTransactionActivity::class.java)
            startActivity(intent)
        }
        return componentView
    }
}
