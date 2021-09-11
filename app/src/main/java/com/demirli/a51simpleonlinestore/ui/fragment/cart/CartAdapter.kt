package com.demirli.a51simpleonlinestore.ui.fragment.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demirli.a51simpleonlinestore.R
import com.demirli.a51simpleonlinestore.model.Product
import com.demirli.a51simpleonlinestore.ui.fragment.products.ProductsAdapter
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso

class CartAdapter(var context: Context, var productList: List<Product>): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.setText(productList[position].product_name)
        Picasso.get().load(productList[position].product_thumbnail_url).into(holder.productThumbnail)
        holder.productPrice.setText("Price: " + productList[position].product_price.toString() + "TL")
        holder.quantity.setText("Quantity: " + productList[position].quantity.toString())

        println(productList[position].product_price.toString())
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val productName = view.findViewById<TextView>(R.id.product_name)
        val productThumbnail = view.findViewById<ImageView>(R.id.product_thumbnail)
        val productPrice = view.findViewById<TextView>(R.id.product_price)
        val quantity = view.findViewById<TextView>(R.id.quantity)
    }
}