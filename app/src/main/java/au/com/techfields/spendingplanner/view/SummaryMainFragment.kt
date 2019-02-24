package au.com.techfields.spendingplanner.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import au.com.techfields.spendingplanner.R
class SummaryMainFragment : Fragment() {
    private val mArray:ArrayList<String> = arrayListOf()
    private lateinit var mListArrayAdapter: ArrayAdapter<String>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_summary_main, container, false)
    }
}
