package com.upclicks.ffc.ui.products.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.databinding.ItemOrderDetailsProductBinding
import com.upclicks.ffc.databinding.ItemProductForLinearBinding
import com.upclicks.ffc.ui.orders.OrderDetails
import com.upclicks.ffc.ui.products.model.Product

class OrderDetailsProductAdapter(
    val context: Context,
    private var productList: List<OrderDetails.OrderDetailsProduct>,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<OrderDetailsProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderDetailsProductBinding.inflate(inflater, parent, false)
        // return the view holder
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.product = productList[position]
//        holder.binding.overlayFrame.backgroundTintList = ColorStateList.valueOf(CustomColorHelper.generateRandomColor(context))
        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
    }
    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return productList.size

    }
    class ViewHolder(val binding: ItemOrderDetailsProductBinding) : RecyclerView.ViewHolder(binding.root) {}

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}