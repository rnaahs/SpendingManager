package au.com.techfields.spendingplanner.viewmodel.init

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import au.com.techfields.spendingplanner.R
import au.com.techfields.spendingplanner.view.activity.AddTransactionActivity
import au.com.techfields.spendingplanner.view.fragment.ExpenseSubFragment
import au.com.techfields.spendingplanner.view.fragment.IncomeSubFragment
import au.com.techfields.spendingplanner.viewmodel.pager.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_add_transaction.*

class SubFragmentTabsInit(activity: AddTransactionActivity) {
    private val mFragmentPagerAdapter: FragmentPagerAdapter = FragmentPagerAdapter(activity.supportFragmentManager)
    private val mViewPager: ViewPager = activity.subpager
    private val mTabLayout: TabLayout = activity.subtabs

    init {
        setTabFragments(mFragmentPagerAdapter, activity)
        mViewPager.adapter = mFragmentPagerAdapter
        mTabLayout.setupWithViewPager(mViewPager)
        setTabIcons(mTabLayout)
        setTabListener(mTabLayout)
    }

    private fun setTabFragments(fragmentPagerAdapter: FragmentPagerAdapter, activity: AddTransactionActivity) {
        fragmentPagerAdapter.addFragment(IncomeSubFragment(), activity.resources.getString(R.string.tab_title_income))
        fragmentPagerAdapter.addFragment(ExpenseSubFragment(), activity.resources.getString(R.string.tab_title_expense))
    }

    private fun setTabIcons(tabLayout: TabLayout) {
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_expense_white)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_income_black)
    }

    private fun setTabListener(tabLayout: TabLayout) {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> tab.setIcon(R.drawable.ic_expense_white)
                    1 -> tab.setIcon(R.drawable.ic_income_white)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                when(tab.position) {
                    0 -> tab.setIcon(R.drawable.ic_expense_black)
                    1 -> tab.setIcon(R.drawable.ic_income_black)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}