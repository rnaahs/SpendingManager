package au.com.techfields.spendingplanner.viewmodel

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import au.com.techfields.spendingplanner.R
import au.com.techfields.spendingplanner.view.MainActivity
import au.com.techfields.spendingplanner.viewmodel.MonthPickerInit.Companion.dateInstance
import au.com.techfields.spendingplanner.viewmodel.MonthPickerInit.Companion.dateToString
import au.com.techfields.spendingplanner.viewmodel.MonthPickerInit.Companion.yearInstance
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*

class MonthItemAdapter(private val activity: MainActivity, private val fragmentTransactionTabsInit: FragmentTransactionTabsInit, private val monthsList: IntArray, private val popupWindow: PopupWindow) : RecyclerView.Adapter<MonthItemAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mMonthBtn: Button = view.findViewById(R.id.month_name_btn)
        val monthInstance: Calendar = Calendar.getInstance()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.month_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val month = monthsList[position]
        holder.monthInstance.set(Calendar.MONTH, month)
        holder.mMonthBtn.text = dateToString(holder.monthInstance.time, "MMM")
        holder.mMonthBtn.setOnClickListener {
            dateInstance.set(Calendar.MONTH, month)
            dateInstance.set(Calendar.YEAR, yearInstance.get(Calendar.YEAR))
            activity.month_year_filtered_btn.text = dateToString(dateInstance.time, "MMM yyyy")
            DatabaseAdapter.mDatabaseAdapter.sortTransactionComponentsByDate(dateInstance)
            DatabaseAdapter.mDatabaseAdapter.sortCategoriesByDate()
            fragmentTransactionTabsInit.notifyFragmentAdaptersChanged()
            popupWindow.dismiss()
        }
    }

    override fun getItemCount(): Int {
        return monthsList.size
    }
}