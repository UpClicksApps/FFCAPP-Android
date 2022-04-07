package com.upclicks.ffc.ui.products.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.R
import com.upclicks.ffc.databinding.ItemOrderBinding
import com.upclicks.ffc.databinding.ItemOrderStatusSelectionBinding
import com.upclicks.ffc.ui.products.model.Order

class OrderStatusSelectionAdapter(
    val context: Context,
    private var statusList: List<String>,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<OrderStatusSelectionAdapter.ViewHolder>() {
    var lastSelectedItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderStatusSelectionBinding.inflate(inflater, parent, false)
        // return the view holder
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.binding.overlayFrame.backgroundTintList = ColorStateList.valueOf(CustomColorHelper.generateRandomColor(context))
        if(lastSelectedItem==position){
            holder.binding.name.setTextColor(ColorStateList.valueOf(context!!.resources.getColor(
                R.color.secondary)))
            holder.binding.line.visibility = View.VISIBLE

        }else {

            holder.binding.name.setTextColor(ColorStateList.valueOf(context!!.resources.getColor(
                R.color.black)))
            holder.binding.line.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            lastSelectedItem = position
            notifyDataSetChanged()
        }
    }


    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return 6
    }

    class ViewHolder(val binding: ItemOrderStatusSelectionBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


}