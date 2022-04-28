package com.upclicks.ffc.ui.notification.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.R
import com.upclicks.ffc.databinding.ItemNotificationBinding
import com.upclicks.ffc.ui.notification.data.model.Notification


class NotificationAdapter(
    val context: Context?,
    private val notificationList: List<Notification>,
    private val onNotificationItemClicked: (Notification) -> Unit,
    private val onOptionsBtnClicked: (Notification, View) -> Unit
) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNotificationBinding.inflate(inflater, parent, false)
        // return the view holder
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.binding.notification = notificationList[position]
//        if (notificationList[position].notificationState == 0) {
//            holder.binding.notificationCard.setCardBackgroundColor(
//                ColorStateList.valueOf(
//                    context!!.resources.getColor(
//                        R.color.white
//                    )
//                )
//            )
//        } else
//            holder.binding.notificationCard.setCardBackgroundColor(
//                ColorStateList.valueOf(
//                    context!!.resources.getColor(
//                        R.color.line_color_grey
//                    )
//                )
//            )
        holder.binding.moreBtn.setOnClickListener {
            onOptionsBtnClicked(Notification(),it)
//            onOptionsBtnClicked(notificationList[position],it)
        }
//        holder.itemView.setOnClickListener {
//            onNotificationItemClicked(notificationList[position])
//        }
    }

    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return 15
    }

    class ViewHolder(val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}