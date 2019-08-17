package au.com.techfields.spendingplanner.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import au.com.techfields.spendingplanner.R

class ReportMainFragment : Fragment() {
    lateinit var mReportView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mReportView = inflater.inflate(R.layout.fragment_main_report, container, false)
        return mReportView
    }
}
