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
import android.util.Log

class TransactionComponentAdapter(private val mTransactionComponentArrayList: ArrayList<ArrayList<Transaction>>, private val mContext: Context) : RecyclerView.Adapter<TransactionComponentAdapter.MyViewHolder>() {
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
        val transactionItemArrayList: ArrayList<Transaction> = mTransactionComponentArrayList[position]
        //Only if the transaction data exists, create a component and its items according to date assorted
        if(transactionItemArrayList.size > 0) {
            var expenseTotalAmount: Double = 0.0
            var incomeTotalAmount: Double = 0.0
            val incomeString = "Income:"
            val expesneString = "Expense:"
            val calendar = transactionItemArrayList[0].mCalendar
            holder.mDateTv.text = calendar.get(Calendar.DATE).toString()
            for(transaction in transactionItemArrayList) {
                if(transaction.mType == Transaction.TransactionType.Income) incomeTotalAmount += transaction.mAmount
                else expenseTotalAmount += transaction.mAmount * (-1)
            }
            if(incomeTotalAmount > 0) setTotalAmountToString(incomeString, incomeTotalAmount, holder)
            if(expenseTotalAmount > 0) setTotalAmountToString(expesneString, expenseTotalAmount, holder)

            holder.mTransactionItemRv.adapter = TransactionItemAdapter(transactionItemArrayList)
        }
    }

    private fun setTotalAmountToString(typeString: String, totalAmount: Double, holder: MyViewHolder) {
        val totalTypeString = "$typeString \$$totalAmount"
        val spannableTypeTotalString: SpannableString = SpannableString(totalTypeString)
        val layoutMarginParams: ViewGroup.MarginLayoutParams
        //Todo: Separate if statement with another method
        if(typeString == "Income:") {
            spannableTypeTotalString.setSpan(ForegroundColorSpan(
                    ContextCompat.getColor(mContext, R.color.incomeColor)), totalTypeString.substring(0, typeString.length).length, totalTypeString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            layoutMarginParams = holder.mIncomeTotalTv.layoutParams as ViewGroup.MarginLayoutParams
            holder.mIncomeTotalTv.setText(spannableTypeTotalString, TextView.BufferType.SPANNABLE)
        }
        else {
            spannableTypeTotalString.setSpan(ForegroundColorSpan(
                    ContextCompat.getColor(mContext, R.color.expenseColor)), totalTypeString.substring(0, typeString.length).length, totalTypeString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            layoutMarginParams = holder.mExpenseTotalTv.layoutParams as ViewGroup.MarginLayoutParams
            holder.mExpenseTotalTv.setText(spannableTypeTotalString, TextView.BufferType.SPANNABLE)
        }
        layoutMarginParams.marginEnd = 10.dp
    }

    override fun getItemCount(): Int {
        return mTransactionComponentArrayList.size
    }

}
