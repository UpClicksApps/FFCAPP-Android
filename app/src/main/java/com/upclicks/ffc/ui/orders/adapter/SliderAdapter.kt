package com.upclicks.ffc.ui.orders.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.commons.OrderStatus
import com.upclicks.ffc.databinding.ItemOrderBinding
import com.upclicks.ffc.databinding.ItemSliderBinding
import com.upclicks.ffc.ui.general.slider.model.Slider
import com.upclicks.ffc.ui.orders.model.Order

class SliderAdapter(
    val context: Context,
    private var sliderList: List<Slider>,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<SliderAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSliderBinding.inflate(inflater, parent, false)
        // return the view holder
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.url = sliderList[position].imagePath!!
        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
    }


    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return sliderList.size
    }

    class ViewHolder(val binding: ItemSliderBinding) : RecyclerView.ViewHolder(binding.root) {}

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


}