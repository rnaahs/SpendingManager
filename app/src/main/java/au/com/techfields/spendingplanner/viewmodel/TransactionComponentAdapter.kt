package au.com.techfields.spendingplanner.viewmodel

import au.com.techfields.spendingplanner.R
import au.com.techfields.spendingplanner.model.Transaction
import java.util.*

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v4.content.ContextCompat
import android.content.res.Resources
import android.content.Context
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.SpannableString

class TransactionComponentAdapter(private val mTransactionComponentList: ArrayList<ArrayList<Transaction>>, private val mContext: Context) : RecyclerView.Adapter<TransactionComponentAdapter.MyViewHolder>() {
    val Int.dp get() = (this * Resources.getSystem().displayMetrics.density).toInt()
    inner class MyViewHolder(view: View, currentViewPosition: Int) : RecyclerView.ViewHolder(view) {
        private val mCardView: CardView = view.findViewById(R.id.card_view)
        val mDateTv: TextView = view.findViewById(R.id.date_Tv)
        val mExpenseTotalTv: TextView = view.findViewById(R.id.expense_total_Tv)
        val mIncomeTotalTv: TextView = view.findViewById(R.id.income_total_Tv)
        val mTransactionItemRv: RecyclerView = view.findViewById(R.id.transaction_item_rv)
        init {
            mTransactionItemRv.layoutManager = LinearLayoutManager(view.context)
            val layoutMarginParams = mCardView.layoutParams as ViewGroup.MarginLayoutParams
            if(currentViewPosition == itemCount - 1) layoutMarginParams.bottomMargin = 10.dp
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val componentView = LayoutInflater.from(parent.context)
                .inflate(R.layout.transaction_component, parent, false)
        return MyViewHolder(componentView, parent.childCount)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val transactionArrayList: ArrayList<Transaction> = mTransactionComponentList[position]
        //Only if the transaction data exists, create a component and its items according to date assorted
        //TODO: pass the date data as an argument
        //if(date != null) { holder.mDateTv.text = date.toString() }
        if(transactionArrayList.size > 0) {
            var expenseTotalAmount: Double = 0.0
            var incomeTotalAmount: Double = 0.0
            val calendar = transactionArrayList[0].mCalendar
            holder.mDateTv.text = calendar.get(Calendar.DATE).toString()
            for(transaction in transactionArrayList) {
                if(transaction.mType.equals(Transaction.TransactionType.Income)) incomeTotalAmount += transaction.mAmount
                else expenseTotalAmount += transaction.mAmount * (-1)
            }

            if(incomeTotalAmount > 0) {
                val incomeTotalString = "Income: \$$incomeTotalAmount"
                val spannableIncomeTotalString = SpannableString(incomeTotalString)
                spannableIncomeTotalString.setSpan(ForegroundColorSpan(
                        ContextCompat.getColor(mContext, R.color.incomeColor)), incomeTotalString.substring(0, 7).length, incomeTotalString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                holder.mIncomeTotalTv.setText(spannableIncomeTotalString, TextView.BufferType.SPANNABLE)
                //margin income total textview
                val layoutMarginParams = holder.mIncomeTotalTv.layoutParams as ViewGroup.MarginLayoutParams
                layoutMarginParams.marginEnd = 10.dp
            }

            if(expenseTotalAmount > 0) {
                val expenseTotalString= "Expense: \$$expenseTotalAmount"
                val spannableExpenseTotalString = SpannableString(expenseTotalString)
                spannableExpenseTotalString.setSpan(ForegroundColorSpan(
                        ContextCompat.getColor(mContext, R.color.expenseColor)), expenseTotalString.substring(0, 8).length, expenseTotalString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                holder.mExpenseTotalTv.setText(spannableExpenseTotalString, TextView.BufferType.SPANNABLE)
                //margin expense total textview
                val layoutMarginParams = holder.mExpenseTotalTv.layoutParams as ViewGroup.MarginLayoutParams
                layoutMarginParams.marginEnd = 10.dp
            }
            holder.mTransactionItemRv.adapter = TransactionItemAdapter(transactionArrayList)
        }
    }

    override fun getItemCount(): Int {
        return mTransactionComponentList.size
    }
}
