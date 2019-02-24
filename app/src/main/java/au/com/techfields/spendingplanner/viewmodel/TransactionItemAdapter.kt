package au.com.techfields.spendingplanner.viewmodel

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import au.com.techfields.spendingplanner.model.Transaction
import java.util.ArrayList

class TransactionItemAdapter(private val mTransactionList: ArrayList<Transaction>) : RecyclerView.Adapter<TransactionComponentAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionComponentAdapter.MyViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: TransactionComponentAdapter.MyViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}