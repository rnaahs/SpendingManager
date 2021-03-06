package au.com.techfields.spendingplanner.viewmodel.item

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import au.com.techfields.spendingplanner.R
import au.com.techfields.spendingplanner.model.Transaction
import java.lang.StringBuilder
import java.util.ArrayList

class TransactionItemAdapter(private val mTransactionItemList: ArrayList<Transaction>) : RecyclerView.Adapter<TransactionItemAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mNameTv:TextView = view.findViewById(R.id.transaction_item_name_tv)
        val mCategoryTv:TextView = view.findViewById(R.id.transaction_item_category_tv)
        val mAmountTv:TextView = view.findViewById(R.id.transaction_item_amount_tv)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val transactionItemList: Transaction = mTransactionItemList[position]
        holder.mNameTv.text = transactionItemList.mName
        holder.mCategoryTv.text = transactionItemList.mCategoryId
        if(transactionItemList.mAmount > 0) holder.mAmountTv.text = StringBuilder().append(holder.itemView.resources.getString(R.string.currency_dollar_sign)).append(transactionItemList.mAmount)
        else holder.mAmountTv.text = "-\$${-1*transactionItemList.mAmount}"
    }

    override fun getItemCount(): Int {
        return mTransactionItemList.size
    }
}