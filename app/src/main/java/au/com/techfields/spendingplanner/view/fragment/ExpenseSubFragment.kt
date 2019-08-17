package au.com.techfields.spendingplanner.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.com.techfields.spendingplanner.R

class ExpenseSubFragment: Fragment() {
    lateinit var mExpenseView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mExpenseView = inflater.inflate(R.layout.fragment_sub_expense, container, false)
        return mExpenseView
    }
}