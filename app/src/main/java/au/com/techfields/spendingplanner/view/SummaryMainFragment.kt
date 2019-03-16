package au.com.techfields.spendingplanner.view

import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import au.com.techfields.spendingplanner.R
import au.com.techfields.spendingplanner.viewmodel.DatabaseAdapter

class SummaryMainFragment : Fragment() {
    private lateinit var mIncomeCategoryRv: RecyclerView
    private lateinit var mExpenseCategoryRv: RecyclerView
    private lateinit var mSummaryTotalIncomeAmountTv: TextView
    private lateinit var mSummaryTotalExpenseAmountTv: TextView
    private lateinit var mSummaryTotalStatusTv: TextView
    private lateinit var mSummaryTotalAmountTv: TextView
    private val mDatabaseAdapter = DatabaseAdapter.mDatabaseAdapter
    private val Int.dp get() = (this * Resources.getSystem().displayMetrics.density).toInt()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_summary_main, container, false)
        val totalIncomeAmount = mDatabaseAdapter.getTotalAmount(mDatabaseAdapter.mSummaryIncomeArrayList)
        val totalExpenseAmount = mDatabaseAdapter.getTotalAmount(mDatabaseAdapter.mSummaryExpenseArrayList)
        val totalAmount = mDatabaseAdapter.getTotalAmount(mDatabaseAdapter.mSummaryIncomeArrayList, mDatabaseAdapter.mSummaryExpenseArrayList)
        mSummaryTotalIncomeAmountTv = view.findViewById(R.id.total_income_amount_tv)
        mSummaryTotalIncomeAmountTv.text = setTotalAmountText(totalIncomeAmount)
        mSummaryTotalIncomeAmountTv.setTextColor(ContextCompat.getColor(view.context, R.color.incomeColor))

        mSummaryTotalExpenseAmountTv = view.findViewById(R.id.total_expense_amount_tv)
        mSummaryTotalExpenseAmountTv.text = setTotalAmountText(totalExpenseAmount)

        mSummaryTotalStatusTv = view.findViewById(R.id.total_status_tv)
        mSummaryTotalStatusTv.text = setTotalStatusText(totalAmount)
        mSummaryTotalAmountTv = view.findViewById(R.id.total_amount_tv)
        mSummaryTotalAmountTv.text = setTotalAmountText(totalAmount)
        mSummaryTotalAmountTv.setTextColor(setTotalAmountColor(totalAmount, view))

        mIncomeCategoryRv = view.findViewById(R.id.income_category_rv)
        mIncomeCategoryRv.layoutManager = LinearLayoutManager(view.context)
        mIncomeCategoryRv.layoutParams.height = 190.dp
        mIncomeCategoryRv.adapter = mDatabaseAdapter.getSummaryTypeAdapter(mDatabaseAdapter.mSummaryIncomeArrayList)

        mExpenseCategoryRv = view.findViewById(R.id.expense_category_rv)
        mExpenseCategoryRv.layoutManager = LinearLayoutManager(view.context)
        mExpenseCategoryRv.layoutParams.height = 190.dp
        mExpenseCategoryRv.adapter = mDatabaseAdapter.getSummaryTypeAdapter(mDatabaseAdapter.mSummaryExpenseArrayList)

        return view
    }

    fun setTotalStatusText(amount: Double) = if(amount < 0) "Overspending" else "Remaining"
    fun setTotalAmountText(amount: Double) = if(amount < 0) "-\$${-1 * amount}" else "+\$$amount"

    private fun setTotalAmountColor(amount: Double, view: View) =
            if(amount > 0) ContextCompat.getColor(view.context, R.color.incomeColor)
            else ContextCompat.getColor(view.context, R.color.expenseColor)
}
