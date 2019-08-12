package au.com.techfields.spendingplanner.viewmodel

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import au.com.techfields.spendingplanner.R
import au.com.techfields.spendingplanner.view.MainActivity
import au.com.techfields.spendingplanner.viewmodel.DatabaseAdapter.Companion.mDatabaseAdapter
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.month_picker_popup.view.*
import java.text.SimpleDateFormat
import java.util.*



class MonthPickerInit(activity: MainActivity, private val fragmentTransactionTabs: FragmentTransactionTabsInit) {
    private val mPopupView: View = activity.layoutInflater.inflate(R.layout.month_picker_popup, activity.window.findViewById(R.id.container))
    private val mYearFilteredTv: TextView = mPopupView.year_filtered_tv
    private val mDateFilteredBtn: Button = activity.month_year_filtered_btn
    private val mYearDecrementBtn: Button = mPopupView.decrement_year_btn
    private val mYearIncrementBtn: Button = mPopupView.increment_year_btn
    private val mMonthDecrementBtn: Button = activity.decrement_month_btn
    private val mMonthIncrementBtn: Button = activity.increment_month_btn
    private val mFirstMonthsRv: RecyclerView = mPopupView.first_months_row
    private val mSecondMonthsRv: RecyclerView = mPopupView.second_months_row
    private val mThisMonthBtn: Button = mPopupView.this_month_btn
    private val mCloseBtn: Button = mPopupView.close_btn

    init {
        val popupWindow = getPopupWindow(activity)
        setOpenPopupWindowWidgets(popupWindow)
        setPopupWindowWidgets(activity, popupWindow)
    }

    companion object {
        var dateInstance: Calendar = Calendar.getInstance()
        var yearInstance: Calendar = Calendar.getInstance()
        fun dateToString(date: Date, pattern: String): String = SimpleDateFormat(pattern, Locale.UK).format(date)
        fun dateToCalendar(date: Date): Calendar {
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar
        }
    }

    private fun getPopupWindow(activity: MainActivity): PopupWindow {
        val popupWindow = PopupWindow(mPopupView, mDatabaseAdapter
                .getResizedWidth(95, mPopupView), mDatabaseAdapter.getResizedHeight(35, mPopupView), true)
        popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(activity.baseContext, R.drawable.ic_cardview_border))

        return popupWindow
    }

    private fun setPopupWindowWidgets(activity: MainActivity, popupWindow: PopupWindow) {
        val firstMonthsList= intArrayOf(Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH, Calendar.APRIL, Calendar.MAY, Calendar.JUNE)
        val secondMonthsList= intArrayOf(Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER, Calendar.OCTOBER, Calendar.NOVEMBER, Calendar.DECEMBER)
        mYearFilteredTv.text = dateToString(dateInstance.time, "yyyy")
        mYearIncrementBtn.setOnClickListener { setDateFilteredWidget(Calendar.YEAR, 1) }
        mYearDecrementBtn.setOnClickListener { setDateFilteredWidget(Calendar.YEAR, -1) }
        mThisMonthBtn.setOnClickListener {
            dateInstance = Calendar.getInstance()
            yearInstance = Calendar.getInstance()
            mDateFilteredBtn.text = dateToString(dateInstance.time, "MMM yyyy")
            mDatabaseAdapter.sortTransactionComponentsByDate(dateInstance)
            mDatabaseAdapter.sortCategoriesByDate()
            fragmentTransactionTabs.notifyFragmentAdaptersChanged()
            popupWindow.dismiss()
        }
        mCloseBtn.setOnClickListener { popupWindow.dismiss() }
        mFirstMonthsRv.adapter = MonthItemAdapter(activity, fragmentTransactionTabs, firstMonthsList, popupWindow)
        mSecondMonthsRv.adapter = MonthItemAdapter(activity, fragmentTransactionTabs, secondMonthsList, popupWindow)
        mFirstMonthsRv.layoutManager = LinearLayoutManager(mPopupView.context, LinearLayoutManager.HORIZONTAL, false)
        mSecondMonthsRv.layoutManager = LinearLayoutManager(mPopupView.context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setOpenPopupWindowWidgets(popupWindow: PopupWindow) {
        mDateFilteredBtn.text = dateToString(dateInstance.time, "MMM yyyy")
        mDateFilteredBtn.setOnClickListener {
            if (popupWindow.isShowing) popupWindow.dismiss()
            else {
                yearInstance.set(Calendar.YEAR, dateInstance.get(Calendar.YEAR))
                mYearFilteredTv.text = dateToString(yearInstance.time, "yyyy")
                popupWindow.showAsDropDown(mDateFilteredBtn, -180, -20)
            }
        }
        mMonthIncrementBtn.setOnClickListener {
            setDateFilteredWidget(Calendar.MONTH, 1)
            mDatabaseAdapter.sortTransactionComponentsByDate(dateInstance)
            mDatabaseAdapter.sortCategoriesByDate()
            fragmentTransactionTabs.notifyFragmentAdaptersChanged()
        }
        mMonthDecrementBtn.setOnClickListener {
            setDateFilteredWidget(Calendar.MONTH, -1)
            mDatabaseAdapter.sortTransactionComponentsByDate(dateInstance)
            mDatabaseAdapter.sortCategoriesByDate()
            fragmentTransactionTabs.notifyFragmentAdaptersChanged()
        }
    }

    private fun setDateFilteredWidget(calendarFormat: Int, dateToIncrement: Int) {
        when (calendarFormat) {
            Calendar.YEAR -> {
                yearInstance.add(calendarFormat, dateToIncrement)
                mYearFilteredTv.text = dateToString(yearInstance.time, "yyyy")
            }
            Calendar.MONTH -> {
                dateInstance.add(calendarFormat, dateToIncrement)
                mDateFilteredBtn.text = dateToString(dateInstance.time, "MMM yyyy")
            }
        }
    }
}