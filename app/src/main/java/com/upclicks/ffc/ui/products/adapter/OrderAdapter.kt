package com.upclicks.ffc.ui.products.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.databinding.ItemOrderBinding
import com.upclicks.ffc.ui.products.model.Order

class OrderAdapter(
    val context: Context,
    private var orderList: List<Order>,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderBinding.inflate(inflater, parent, false)
        // return the view holder
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.binding.overlayFrame.backgroundTintList = ColorStateList.valueOf(CustomColorHelper.generateRandomColor(context))
        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
    }


    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return 15
    }

    class ViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {}

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


}