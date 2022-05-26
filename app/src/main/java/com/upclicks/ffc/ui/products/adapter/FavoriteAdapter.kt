package com.upclicks.ffc.ui.products.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.databinding.ItemFavoriteBinding
import com.upclicks.ffc.ui.products.model.Product

class FavoriteAdapter(
    val context: Context,
    private var productList: List<Product>,
    private val onRemovedClicked: (Product) -> Unit,
    private val onCartClicked: (Product) -> Unit,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoriteBinding.inflate(inflater, parent, false)
        // return the view holder
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.binding.overlayFrame.backgroundTintList = ColorStateList.valueOf(CustomColorHelper.generateRandomColor(context))
        holder.binding.product = productList[position]
        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
        holder.binding.removeIv.setOnClickListener {
            onRemovedClicked(productList[position])
        }
        holder.binding.cartIv.setOnClickListener {
            onCartClicked(productList[position])
        }
    }


    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return productList.size
    }

    class ViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {}

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


}