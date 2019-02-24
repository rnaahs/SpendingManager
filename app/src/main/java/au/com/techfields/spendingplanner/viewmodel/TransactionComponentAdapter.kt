package au.com.techfields.spendingplanner.viewmodel

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import au.com.techfields.spendingplanner.R
import au.com.techfields.spendingplanner.model.Transaction

import java.util.ArrayList

class TransactionComponentAdapter(private val mTransactionList: ArrayList<ArrayList<Transaction>>) : RecyclerView.Adapter<TransactionComponentAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mDateTv: TextView
        var mTransactionItemRv: RecyclerView

        init {
            mDateTv = view.findViewById(R.id.date_Tv)
            mTransactionItemRv = view.findViewById(R.id.transaction_item_rv)
            mTransactionItemRv.layoutManager = LinearLayoutManager(view.context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.transaction_component, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val transactionArrayList: ArrayList<Transaction> = mTransactionList[position]
        if(transactionArrayList.size > 0) {
            val date = transactionArrayList[0].mDate
            holder.mDateTv.text = date.time.toString()
            holder.mTransactionItemRv.adapter = TransactionItemAdapter(transactionArrayList)
        }

    }

    override fun getItemCount(): Int {
        return mTransactionList.size
    }
}
