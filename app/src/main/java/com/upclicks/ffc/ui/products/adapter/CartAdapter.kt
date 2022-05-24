package com.upclicks.ffc.ui.products.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.upclicks.ffc.R
import com.upclicks.ffc.databinding.ItemCartBinding
import com.upclicks.ffc.ui.cart.model.Cart
import com.upclicks.ffc.ui.general.dialog.ConfirmDialog

class CartAdapter(
    val context: Context,
    private var cartList: List<Cart>,
    private val onQuantityChanged: (Cart, Int) -> Unit,
    private val onItemRemoved: (Cart) -> Unit
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
        holder.binding.cart = cartList[position]
        holder.binding.quantity.setValueChangedListener { value, action ->
            if (value == 0)
                ConfirmDialog(context,
                    context.getString(R.string.delete),
                    context.getString(R.string.are_you_sure),
                    onYesBtnClick = {

                        notifyItemRemoved(position)
                        onItemRemoved(cartList[position])
                    },
                    onNoBtnClick = {
                        holder.binding.quantity.value = 1
                    }).show()
            else
                onQuantityChanged(cartList[position], value)
        }
    }


    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return ArrayList<Cart>(cartList).size
    }

    class ViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {}

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


}