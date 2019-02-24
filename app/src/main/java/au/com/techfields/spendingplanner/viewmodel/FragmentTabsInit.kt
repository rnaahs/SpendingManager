package au.com.techfields.spendingplanner.viewmodel

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import au.com.techfields.spendingplanner.R
import au.com.techfields.spendingplanner.view.ReportMainFragment
import au.com.techfields.spendingplanner.view.SummaryMainFragment
import au.com.techfields.spendingplanner.view.TransactionMainFragment

class FragmentTabsInit(fragmentPagerAdapter: FragmentPagerAdapter, tabLayout: TabLayout, viewPager: ViewPager) {
    init {
        setTabFragments(fragmentPagerAdapter)
        viewPager.adapter = fragmentPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        setTabIcons(tabLayout)
        setTabListener(tabLayout)
    }

    private fun setTabFragments(fragmentPagerAdapter: FragmentPagerAdapter) {
        fragmentPagerAdapter.addFragment(TransactionMainFragment(), "Transaction")
        fragmentPagerAdapter.addFragment(SummaryMainFragment(), "Summary")
        fragmentPagerAdapter.addFragment(ReportMainFragment(), "Report")
    }

    private fun setTabIcons(tabLayout: TabLayout) {
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_transaction_white)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_summary_black)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_report_black)
    }

    private fun setTabListener(tabLayout: TabLayout) {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> tab.setIcon(R.drawable.ic_transaction_white)
                    1 -> tab.setIcon(R.drawable.ic_summary_white)
                    2 -> tab.setIcon(R.drawable.ic_report_white)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> tab.setIcon(R.drawable.ic_transaction_black)
                    1 -> tab.setIcon(R.drawable.ic_summary_black)
                    2 -> tab.setIcon(R.drawable.ic_report_black)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) { }
        })
    }
}