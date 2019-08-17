package au.com.techfields.spendingplanner.viewmodel.component

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
import android.util.DisplayMetrics
import au.com.techfields.spendingplanner.viewmodel.init.CalendarMonthPickerInit.Companion.dateToString
import au.com.techfields.spendingplanner.viewmodel.item.TransactionItemAdapter

class TransactionComponentAdapter(private val mTransactionComponentArrayList: ArrayList<ArrayList<Transaction>>, private val mContext: Context) : RecyclerView.Adapter<TransactionComponentAdapter.MyViewHolder>() {
    private val Int.dp get() = this * Resources.getSystem().displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mCardView: CardView = view.findViewById(R.id.card_view)
        val mDateTv: TextView = view.findViewById(R.id.date_Tv)
        val mExpenseTotalTv: TextView = view.findViewById(R.id.expense_total_Tv)
        val mIncomeTotalTv: TextView = view.findViewById(R.id.income_total_Tv)
        val mTransactionItemRv: RecyclerView = view.findViewById(R.id.transaction_item_rv)

        init {
            mTransactionItemRv.layoutManager = LinearLayoutManager(view.context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val componentView = LayoutInflater.from(parent.context)
                .inflate(R.layout.component_transaction, parent, false)
        return MyViewHolder(componentView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val transactionItemArrayList: ArrayList<Transaction> = mTransactionComponentArrayList[position]
        //Only if the transaction data exists, create a component and its items according to date assorted
        if (transactionItemArrayList.size > 0) {
            setTransactionTotalAmount(transactionItemArrayList, holder)
            holder.mTransactionItemRv.adapter = TransactionItemAdapter(transactionItemArrayList)
            val layoutMarginParams = holder.mCardView.layoutParams as ViewGroup.MarginLayoutParams
            if (position == 0) layoutMarginParams.topMargin = 10.dp
        }
    }

    private fun setTransactionTotalAmount(transactionItemArrayList: ArrayList<Transaction>, holder: MyViewHolder) {
        var expenseTotalAmount = 0.0
        var incomeTotalAmount = 0.0
        val incomeLayoutMarginParams: ViewGroup.MarginLayoutParams = holder.mIncomeTotalTv.layoutParams as ViewGroup.MarginLayoutParams
        val expenseLayoutMarginParams: ViewGroup.MarginLayoutParams = holder.mExpenseTotalTv.layoutParams as ViewGroup.MarginLayoutParams
        holder.mDateTv.text = dateToString(transactionItemArrayList[0].mDate, "dd")
        for (transaction in transactionItemArrayList) {
            if (transaction.mType == Transaction.TransactionType.Income.name) incomeTotalAmount += transaction.mAmount
            else expenseTotalAmount += transaction.mAmount * (-1)
        }
        holder.mIncomeTotalTv.text = null
        holder.mExpenseTotalTv.text = null
        expenseLayoutMarginParams.marginEnd = 0.dp
        if (expenseTotalAmount > 0.0) setTotalAmountToText(Transaction.TransactionType.Expense.name, expenseTotalAmount, holder.mExpenseTotalTv, R.color.expenseColor, expenseLayoutMarginParams)
        if (incomeTotalAmount > 0.0) setTotalAmountToText(Transaction.TransactionType.Income.name, incomeTotalAmount, holder.mIncomeTotalTv, R.color.incomeColor, incomeLayoutMarginParams)
    }

    private fun setTotalAmountToText(typeString: String, totalAmount: Double, totalAmountTextView: TextView, colorID: Int, marginLayoutParams: ViewGroup.MarginLayoutParams) {
        val totalTypeString = if(typeString == Transaction.TransactionType.Income.name) "$typeString \$$totalAmount" else "$typeString -\$$totalAmount"
        val spannableTypeTotalString = SpannableString(totalTypeString)

        spannableTypeTotalString.setSpan(ForegroundColorSpan(
                ContextCompat.getColor(mContext, colorID)), totalTypeString.substring(0, typeString.length).length, totalTypeString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        totalAmountTextView.setText(spannableTypeTotalString, TextView.BufferType.SPANNABLE)
        marginLayoutParams.marginEnd = 10.dp
    }

    override fun getItemCount(): Int {
        return mTransactionComponentArrayList.size
    }

}

