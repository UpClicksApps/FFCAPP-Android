package com.upclicks.ffc.ui.general.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.R
import com.upclicks.ffc.databinding.ItemDeliveryTimeBinding
import com.upclicks.ffc.databinding.ItemHelpBinding
import com.upclicks.ffc.ui.general.model.Faq

class FaqAdapter(
    val context: Context,
    private var faqList: List<Faq>

) : RecyclerView.Adapter<FaqAdapter.ViewHolder>() {

    private var lastSelectedItem = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHelpBinding.inflate(inflater, parent, false)
        // return the view holder
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.faq = faqList[position]
        if (lastSelectedItem == position) {
            holder.binding.expandLayout.expand()
            holder.binding.arrowIv.rotation = 180f
            holder.binding.line.visibility = View.GONE
            holder.binding.question.setTextColor(
                ColorStateList.valueOf(
                    context!!.resources.getColor(
                        R.color.primary
                    )
                )
            )
        } else {
            holder.binding.expandLayout.collapse()
            holder.binding.arrowIv.rotation = 0f
            holder.binding.line.visibility = View.VISIBLE
            holder.binding.question.setTextColor(
                ColorStateList.valueOf(
                    context!!.resources.getColor(
                        R.color.black
                    )
                )
            )
        }
        holder.itemView.setOnClickListener {
            if (lastSelectedItem != position) {
                notifyItemChanged(position)
                notifyItemChanged(lastSelectedItem)
                lastSelectedItem = position
            } else {
                lastSelectedItem = -1
                notifyItemChanged(position)
            }
        }
    }
    fun resetUi() {
        lastSelectedItem = -1
    }

    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return faqList.size
    }

    class ViewHolder(val binding: ItemHelpBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


}