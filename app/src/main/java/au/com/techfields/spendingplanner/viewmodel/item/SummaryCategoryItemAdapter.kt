package au.com.techfields.spendingplanner.viewmodel.item

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import au.com.techfields.spendingplanner.R
import au.com.techfields.spendingplanner.model.Category
import java.lang.StringBuilder


class SummaryCategoryItemAdapter(private val mCategoryIncomeList: ArrayList<Category>) : RecyclerView.Adapter<SummaryCategoryItemAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mNameTv: TextView = view.findViewById(R.id.category_name_tv)
        val mTotalAmountTv: TextView = view.findViewById(R.id.category_amount_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_summary, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category: Category = mCategoryIncomeList[position]
        holder.mNameTv.text = category.mName
        if(category.mAmount > 0) holder.mTotalAmountTv.text = StringBuilder().append(holder.itemView.resources.getString(R.string.currency_dollar_sign)).append(category.mAmount)
        else holder.mTotalAmountTv.text = "-\$${-1*category.mAmount}"
    }

    override fun getItemCount(): Int = mCategoryIncomeList.size
}