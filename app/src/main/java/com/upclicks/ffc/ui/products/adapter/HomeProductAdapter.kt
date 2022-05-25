package com.upclicks.ffc.ui.products.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.commons.Keys
import com.upclicks.ffc.databinding.ItemHomeProductBinding
import com.upclicks.ffc.ui.products.ProductDetailsActivity
import com.upclicks.ffc.ui.products.model.HomeProduct
import com.upclicks.ffc.ui.products.model.Product

class HomeProductAdapter(
    val context: Context,
    private var productList: List<HomeProduct>,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<HomeProductAdapter.ViewHolder>() {

    lateinit var productAdapter: ProductLinearAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeProductBinding.inflate(inflater, parent, false)
        // return the view holder
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.category = productList[position]
        productAdapter = ProductLinearAdapter(context, productList[position].products!!, onItemClicked = {
            context.startActivity(
                Intent(
                    context,
                    ProductDetailsActivity::class.java
                ).putExtra(Keys.Intent_Constants.PRODUCT_ID, productList[position].products!![it].id)
            )
        })
        holder.binding.topSaleRv.adapter = productAdapter
        holder.binding.topSaleRv.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
    }
    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return productList.size

    }
    class ViewHolder(val binding: ItemHomeProductBinding) : RecyclerView.ViewHolder(binding.root) {}

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}