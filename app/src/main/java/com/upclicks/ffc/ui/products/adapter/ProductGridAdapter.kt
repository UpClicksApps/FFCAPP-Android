package com.upclicks.ffc.ui.products.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.databinding.ItemProductForGridBinding
import com.upclicks.ffc.ui.products.model.Product

class ProductGridAdapter(
    val context: Context,
    private var productList: List<Product>,
    private val onFavoriteClicked: (Int) -> Unit,
    private val onCartClicked: (Int) -> Unit,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<ProductGridAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductForGridBinding.inflate(inflater, parent, false)
        // return the view holder
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.product = productList[position]
//        holder.binding.overlayFrame.backgroundTintList = ColorStateList.valueOf(CustomColorHelper.generateRandomColor(context))
        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
        holder.binding.cartIv.setOnClickListener {
            onCartClicked(position)
        }

        holder.binding.toggleFavBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isPressed) {
                onFavoriteClicked(position)
            }
        }
    }


    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return productList.size
    }

    class ViewHolder(val binding: ItemProductForGridBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


}