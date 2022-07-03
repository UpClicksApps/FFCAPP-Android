package com.upclicks.ffc.ui.orders.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.R
import com.upclicks.ffc.commons.OrderStatus
import com.upclicks.ffc.databinding.ItemOrderBinding
import com.upclicks.ffc.databinding.ItemTrackOrderBinding
import com.upclicks.ffc.ui.orders.model.OrderStatusLog

class OrderStatusLogAdapter(
    val context: Context,
    private var orderStatusLogList: List<OrderStatusLog>
) : RecyclerView.Adapter<OrderStatusLogAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTrackOrderBinding.inflate(inflater, parent, false)
        // return the view holder
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.orderStatusLog = orderStatusLogList[position]
        when (orderStatusLogList[position].orderStatus) {
            OrderStatus.Confirmed.value,OrderStatus.Returned.value,OrderStatus.Refused.value,OrderStatus.Cancelled.value -> {
                holder.binding.icon.setImageDrawable(context.getDrawable(R.drawable.ic_track_cancel_accepted_returned))
            }
            OrderStatus.Delivered.value -> {
                holder.binding.icon.setImageDrawable(context.getDrawable(R.drawable.ic_track_delivered))
            }
            OrderStatus.OnTheWay.value -> {
                holder.binding.icon.setImageDrawable(context.getDrawable(R.drawable.ic_track_on_the_way))
            }
            OrderStatus.Requested.value -> {
                holder.binding.icon.setImageDrawable(context.getDrawable(R.drawable.ic_track_requested))
            }
        }
    }

    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return orderStatusLogList.size
    }

    class ViewHolder(val binding: ItemTrackOrderBinding) : RecyclerView.ViewHolder(binding.root) {}

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}