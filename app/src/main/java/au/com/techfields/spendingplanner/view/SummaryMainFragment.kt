package au.com.techfields.spendingplanner.view

import android.app.ActionBar
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView

import au.com.techfields.spendingplanner.R
import au.com.techfields.spendingplanner.viewmodel.DatabaseAdapter
import android.view.*
import au.com.techfields.spendingplanner.model.Category
import au.com.techfields.spendingplanner.viewmodel.DatabaseAdapter.Companion.mDatabaseAdapter
import java.util.ArrayList

class SummaryMainFragment : Fragment() {
    lateinit var mIncomeCategoryRv: RecyclerView
    lateinit var mExpenseCategoryRv: RecyclerView
    lateinit var mSummaryTotalIncomeAmountTv: TextView
    lateinit var mSummaryTotalExpenseAmountTv: TextView
    lateinit var mSummaryTotalStatusTv: TextView
    lateinit var mSummaryTotalAmountTv: TextView
    lateinit var mSummaryView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mSummaryView = inflater.inflate(R.layout.fragment_summary_main, container, false)

        mSummaryTotalIncomeAmountTv = mSummaryView.findViewById(R.id.total_income_amount_tv)
        mSummaryTotalIncomeAmountTv.text = setTotalAmountText(mDatabaseAdapter.totalIncomeAmount)
        mSummaryTotalIncomeAmountTv.setTextColor(ContextCompat.getColor(mSummaryView.context, R.color.incomeColor))

        mSummaryTotalExpenseAmountTv = mSummaryView.findViewById(R.id.total_expense_amount_tv)
        mSummaryTotalExpenseAmountTv.text = setTotalAmountText(mDatabaseAdapter.totalExpenseAmount)

        mSummaryTotalStatusTv = mSummaryView.findViewById(R.id.total_status_tv)
        mSummaryTotalStatusTv.text = setTotalStatusText(mDatabaseAdapter.totalAmount)

        mSummaryTotalAmountTv = mSummaryView.findViewById(R.id.total_amount_tv)
        mSummaryTotalAmountTv.text = setTotalAmountText(mDatabaseAdapter.totalAmount)
        mSummaryTotalAmountTv.setTextColor(setTotalAmountColor(mDatabaseAdapter.totalAmount, mSummaryView))

        mIncomeCategoryRv = mSummaryView.findViewById(R.id.income_category_rv)
        mExpenseCategoryRv = mSummaryView.findViewById(R.id.expense_category_rv)
        setRecyclerViewParams(mDatabaseAdapter.mSummaryIncomeArrayList, mDatabaseAdapter.mSummaryExpenseArrayList, mIncomeCategoryRv, mExpenseCategoryRv, mSummaryView)

        return mSummaryView
    }

    fun setTotalStatusText(amount: Double) = if (amount < 0) "Overspending" else if (amount == 0.0) "Balanced" else "Remaining"
    fun setTotalAmountText(amount: Double) = if (amount < 0) "-\$${-1 * amount}" else if (amount == 0.0) "\$$amount" else "+\$$amount"
    fun setTotalAmountColor(amount: Double, view: View) =
            when {
                amount > 0 -> ContextCompat.getColor(view.context, R.color.incomeColor)
                amount < 0 -> ContextCompat.getColor(view.context, R.color.expenseColor)
                else -> ContextCompat.getColor(view.context, R.color.blackColor)
            }

    fun setRecyclerViewParams(summaryIncomeArrayList: ArrayList<Category>, summaryExpenseArrayList: ArrayList<Category>,
                              incomeRecyclerView: RecyclerView, expenseRecyclerView: RecyclerView, view: View) {
        val totalCategorySize = summaryIncomeArrayList.size + summaryExpenseArrayList.size

        incomeRecyclerView.layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT
        expenseRecyclerView.layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT
        incomeRecyclerView.layoutManager = LinearLayoutManager(view.context)
        expenseRecyclerView.layoutManager = LinearLayoutManager(view.context)
        incomeRecyclerView.adapter = mDatabaseAdapter.getSummaryCategoryTypeAdapter(summaryIncomeArrayList)
        expenseRecyclerView.adapter = mDatabaseAdapter.getSummaryCategoryTypeAdapter(summaryExpenseArrayList)


        if (totalCategorySize > 8) {
            if (summaryIncomeArrayList.size > 4 && summaryExpenseArrayList.size > 4) {
                incomeRecyclerView.layoutParams.height = mDatabaseAdapter.getResizedHeight(27, view)
                expenseRecyclerView.layoutParams.height = mDatabaseAdapter.getResizedHeight(27, view)
            }
            else if(summaryIncomeArrayList.size > 4 && summaryExpenseArrayList.size < 4)
                incomeRecyclerView.layoutParams.height =  mDatabaseAdapter.getResizedHeight(55, view) - mDatabaseAdapter.getResizedHeight(8 * summaryExpenseArrayList.size, view)
            else if(summaryIncomeArrayList.size < 4 && summaryExpenseArrayList.size > 4)
                expenseRecyclerView.layoutParams.height = mDatabaseAdapter.getResizedHeight(55, view) - mDatabaseAdapter.getResizedHeight(8 * summaryIncomeArrayList.size, view)
        }

    }
}
