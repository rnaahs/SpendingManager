package au.com.techfields.spendingplanner.viewmodel

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import au.com.techfields.spendingplanner.R
import au.com.techfields.spendingplanner.model.Category


class SummaryTypeAdapter(private val mCategoryIncomeList: ArrayList<Category>) : RecyclerView.Adapter<SummaryTypeAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mNameTv: TextView = view.findViewById(R.id.category_name_tv)
        val mTotalAmountTv: TextView = view.findViewById(R.id.category_amount_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(au.com.techfields.spendingplanner.R.layout.summary_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category: Category = mCategoryIncomeList[position]
        holder.mNameTv.text = category.mName
        holder.mTotalAmountTv.text = setAmountText(category.mAmount)
    }

    override fun getItemCount(): Int = mCategoryIncomeList.size

    private fun setAmountText(amount: Double) = if(amount > 0) "$$amount" else "$${-1 * amount}"
}