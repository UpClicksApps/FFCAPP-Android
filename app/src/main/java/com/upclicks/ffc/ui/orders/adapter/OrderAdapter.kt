package com.upclicks.ffc.ui.orders.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.commons.OrderStatus
import com.upclicks.ffc.databinding.ItemOrderBinding
import com.upclicks.ffc.ui.orders.model.Order

class OrderAdapter(
    val context: Context,
    private var orderList: List<Order>,
    private val onCancelClicked: (Int) -> Unit,
    private val onTrackOrderClicked: (Int) -> Unit,
    private val onDetailsClicked: (Int) -> Unit
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderBinding.inflate(inflater, parent, false)
        // return the view holder
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.order = orderList[position]
        if (orderList[position].orderStatus == OrderStatus.Requested.value) {
            holder.binding.cancelBtn.visibility = View.VISIBLE
            holder.binding.trackOrderBtn.visibility = View.GONE
        } else {
            holder.binding.cancelBtn.visibility = View.GONE
            holder.binding.trackOrderBtn.visibility = View.VISIBLE
        }


        holder.binding.trackOrderBtn.setOnClickListener {
            onTrackOrderClicked(position)
        }
        holder.binding.detailsBtn.setOnClickListener {
            onDetailsClicked(position)
        }
        holder.binding.cancelBtn.setOnClickListener {
            onCancelClicked(position)
        }
    }


    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return orderList.size
    }

    class ViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {}

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


}