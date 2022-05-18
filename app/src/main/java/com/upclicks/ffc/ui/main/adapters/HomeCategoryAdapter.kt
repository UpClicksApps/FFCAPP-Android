package com.upclicks.ffc.ui.main.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.databinding.ItemCategoryHomeBinding
import com.upclicks.ffc.helpers.CustomColorHelper
import com.upclicks.ffc.ui.general.model.Category

class HomeCategoryAdapter(
    val context: Context,
    private var categoryList: List<Category>,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryHomeBinding.inflate(inflater, parent, false)
        // return the view holder
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.category = categoryList[position]
        holder.binding.overlayFrame.backgroundTintList =
            ColorStateList.valueOf(CustomColorHelper.generateRandomColor(context))
        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
    }


    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return categoryList.size
    }

    class ViewHolder(val binding: ItemCategoryHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


}