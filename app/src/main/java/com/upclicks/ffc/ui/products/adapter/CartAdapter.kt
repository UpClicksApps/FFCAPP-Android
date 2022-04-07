package com.upclicks.ffc.ui.products.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.databinding.ItemCartBinding
import com.upclicks.ffc.databinding.ItemFavoriteBinding
import com.upclicks.ffc.databinding.ItemProductHomeForGridBinding
import com.upclicks.ffc.databinding.ItemWalletBinding
import com.upclicks.ffc.ui.products.model.Cart
import com.upclicks.ffc.ui.products.model.Product
import com.upclicks.ffc.ui.products.model.Wallet

class CartAdapter(
    val context: Context,
    private var cartList: List<Cart>,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCartBinding.inflate(inflater, parent, false)
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

    class ViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {}

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


}