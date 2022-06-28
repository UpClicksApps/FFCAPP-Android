package com.upclicks.ffc.ui.checkout.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.R
import com.upclicks.ffc.databinding.ItemDeliveryTimeBinding
import com.upclicks.ffc.ui.products.model.DeliveryTime

class DeliveryTimeAdapter(
    val context: Context,
    private var timeList: List<DeliveryTime>,
    private val onItemClicked: (String) -> Unit,
    private val onItemRemoved: (Boolean) -> Unit

) : RecyclerView.Adapter<DeliveryTimeAdapter.ViewHolder>() {

    private var lastSelectedItem = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDeliveryTimeBinding.inflate(inflater, parent, false)
        // return the view holder
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
//        holder.binding.overlayFrame.backgroundTintList = ColorStateList.valueOf(CustomColorHelper.generateRandomColor(context))
        holder.binding.time = timeList[position].time
        holder.binding.radiobutton.isChecked = lastSelectedItem == position
        if (lastSelectedItem == position) {
            holder.binding.container.backgroundTintList =
                ColorStateList.valueOf(
                    context!!.resources.getColor(
                        R.color.primary_light
                    )
                )
            holder.binding.timeTv.setTextColor(
                ColorStateList.valueOf(
                    context!!.resources.getColor(
                        R.color.primary
                    )
                )
            )
            holder.binding.radiobutton.isChecked = true
        } else {
            holder.binding.container.backgroundTintList =
                ColorStateList.valueOf(
                    context!!.resources.getColor(
                        R.color.white
                    )
                )
            holder.binding.timeTv.setTextColor(
                ColorStateList.valueOf(
                    context!!.resources.getColor(
                        R.color.black
                    )
                )
            )
            holder.binding.radiobutton.isChecked = false
        }
        holder.itemView.setOnClickListener {
            if (lastSelectedItem != position) {
                notifyItemChanged(position)
                notifyItemChanged(lastSelectedItem)
                lastSelectedItem = position
                onItemClicked(timeList[position].time!!)
            } else {
                lastSelectedItem = -1
                notifyItemChanged(position)
                onItemRemoved(true)
            }
        }
        holder.binding.radiobutton.setOnClickListener {
            holder.itemView.performClick()
        }
    }

    fun resetUi() {
        lastSelectedItem = -1
    }

    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return timeList.size
    }

    class ViewHolder(val binding: ItemDeliveryTimeBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


}