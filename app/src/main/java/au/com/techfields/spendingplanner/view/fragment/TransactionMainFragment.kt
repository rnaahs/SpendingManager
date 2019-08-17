package au.com.techfields.spendingplanner.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.com.techfields.spendingplanner.R
import au.com.techfields.spendingplanner.view.activity.AddTransactionActivity
import au.com.techfields.spendingplanner.viewmodel.database.DatabaseAdapter
import au.com.techfields.spendingplanner.viewmodel.component.TransactionComponentAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_main_transaction.view.*

class TransactionMainFragment : Fragment() {
    lateinit var mTransactionComponentRv: RecyclerView
    lateinit var mTransactionView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mTransactionView = inflater.inflate(R.layout.fragment_main_transaction, container, false)
        mTransactionComponentRv = mTransactionView.findViewById(R.id.transaction_rv)
        mTransactionComponentRv.layoutManager = LinearLayoutManager(mTransactionView.context)
        mTransactionComponentRv.adapter = TransactionComponentAdapter(DatabaseAdapter.mDatabaseAdapter.mTransactionComponentArrayList, mTransactionView.context)
        mTransactionView.add_transaction_btn.setOnClickListener {
            val intent = Intent(mTransactionView.context, AddTransactionActivity::class.java)
            startActivityForResult(intent, container!!.resources.getInteger(R.integer.add_transaction_code))
        }
        return mTransactionView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //Respond sth based on the previous activity
    }
}
