package au.com.techfields.spendingplanner.viewmodel

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import au.com.techfields.spendingplanner.R
import au.com.techfields.spendingplanner.view.MainActivity
import au.com.techfields.spendingplanner.view.ReportMainFragment
import au.com.techfields.spendingplanner.view.SummaryMainFragment
import au.com.techfields.spendingplanner.view.TransactionMainFragment
import au.com.techfields.spendingplanner.viewmodel.DatabaseAdapter.Companion.mDatabaseAdapter
import kotlinx.android.synthetic.main.app_bar_main.*

class FragmentTabsInit(activity: MainActivity) {
    private val mFragmentPagerAdapter: FragmentPagerAdapter = FragmentPagerAdapter(activity.supportFragmentManager)
    private val mViewPager: ViewPager = activity.pager
    private val mTabLayout: TabLayout = activity.tabs

    init {
        setTabFragments(mFragmentPagerAdapter)
        mViewPager.adapter = mFragmentPagerAdapter
        mTabLayout.setupWithViewPager(mViewPager)
        setTabIcons(mTabLayout)
        setTabListener(mTabLayout)
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

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    fun notifyFragmentAdaptersChanged() {
        val transactionFragment = mFragmentPagerAdapter.getItem(0) as TransactionMainFragment
        val summaryFragment = mFragmentPagerAdapter.getItem(1) as SummaryMainFragment
        transactionFragment.mTransactionComponentRv.adapter.notifyDataSetChanged()
        with(summaryFragment) {
            mExpenseCategoryRv.adapter.notifyDataSetChanged()
            mIncomeCategoryRv.adapter.notifyDataSetChanged()
            setAdapterProperties(this)
        }
    }

    private fun setAdapterProperties(summaryFragment: SummaryMainFragment) {
        with(summaryFragment) {
            with(DatabaseAdapter.mDatabaseAdapter) {
                totalIncomeAmount = getTotalAmount(mSummaryIncomeArrayList)
                totalExpenseAmount = getTotalAmount(mSummaryExpenseArrayList)
                totalAmount = getTotalAmount(mSummaryIncomeArrayList, mSummaryExpenseArrayList)
                summaryFragment.setRecyclerViewParams(mSummaryIncomeArrayList, mSummaryExpenseArrayList, mIncomeCategoryRv, mExpenseCategoryRv, mSummaryView)
            }
            mSummaryTotalIncomeAmountTv.text = setTotalAmountText(mDatabaseAdapter.totalIncomeAmount)
            mSummaryTotalExpenseAmountTv.text = setTotalAmountText(mDatabaseAdapter.totalExpenseAmount)
            mSummaryTotalStatusTv.text = setTotalStatusText(mDatabaseAdapter.totalAmount)
            mSummaryTotalAmountTv.text = setTotalAmountText(mDatabaseAdapter.totalAmount)
            mSummaryTotalAmountTv.setTextColor(setTotalAmountColor(mDatabaseAdapter.totalAmount, mSummaryView))
        }
    }
}