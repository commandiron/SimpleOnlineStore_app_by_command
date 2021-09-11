package com.demirli.a51simpleonlinestore.ui.fragment.products

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demirli.a51simpleonlinestore.R
import com.demirli.a51simpleonlinestore.model.Product
import com.google.android.material.button.MaterialButton
import com.shawnlin.numberpicker.NumberPicker
import com.squareup.picasso.Picasso

class ProductsAdapter(var context: Context, var productList: List<Product>, var onCartButtonClick: OnCartButtonClick): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.setText(productList[position].product_name)
        Picasso.get().load(productList[position].product_thumbnail_url).into(holder.productThumbnail)
        holder.productShortDescription.setText(productList[position].product_short_description)
        holder.productPrice.setText("Price: " + productList[position].product_price.toString() + "TL")


        holder.addCartButton.setOnClickListener {
            val product = productList[position]
            product.quantity = holder.numberPicker.value
            onCartButtonClick.onCartButtonClick(product)
        }



    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val productName = view.findViewById<TextView>(R.id.product_name)
        val productThumbnail = view.findViewById<ImageView>(R.id.product_thumbnail)
        val productShortDescription = view.findViewById<TextView>(R.id.product_short_description)
        val productPrice = view.findViewById<TextView>(R.id.product_price)
        val addCartButton = view.findViewById<MaterialButton>(R.id.add_cart_button)
        val numberPicker = view.findViewById<NumberPicker>(R.id.numberPicker)
    }

    interface OnCartButtonClick{
        fun onCartButtonClick(product: Product)
    }

}