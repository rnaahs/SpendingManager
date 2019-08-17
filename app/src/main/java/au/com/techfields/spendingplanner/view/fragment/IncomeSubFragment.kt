package au.com.techfields.spendingplanner.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.com.techfields.spendingplanner.R

class IncomeSubFragment: Fragment() {
    lateinit var mIncomeView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mIncomeView = inflater.inflate(R.layout.fragment_sub_income, container, false)
        return mIncomeView
    }
}