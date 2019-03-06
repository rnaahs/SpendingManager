package au.com.techfields.spendingplanner.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import au.com.techfields.spendingplanner.R
class SummaryMainFragment : Fragment() {
    private lateinit var mIncomeRv: RecyclerView
    private lateinit var mExpenseRv: RecyclerView
    private lateinit var mIncomeAdapter: IncomeAdapter
    private lateinit var mExpenseAdapter: ExpenseAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_summary_main, container, false)
        mIncomeRv = view.findViewById(R.id.income_rv)
        mIncomeRv.setLayoutManager(LinearLayoutManager(activity))
        mIncomeRv.addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
        mIncomeRv.removeItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
        mIncomeRv.setItemAnimator(DefaultItemAnimator())
        mIncomeRv.setAdapter(mIncomeAdapter)

        mExpenseRv = view.findViewById(R.id.expense_rv) as RecyclerView
        mExpenseRv.setLayoutManager(LinearLayoutManager(activity))
        mExpenseRv.addItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
        mExpenseRv.removeItemDecoration(DividerItemDecoration(context!!, LinearLayoutManager.VERTICAL))
        mExpenseRv.setItemAnimator(DefaultItemAnimator())
        mExpenseRv.setAdapter(mExpenseAdapter)
        return view
    }
}
