package com.demirli.a51simpleonlinestore.ui.fragment.orders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demirli.a51simpleonlinestore.R
import com.demirli.a51simpleonlinestore.model.Cart
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class OrdersAdapter(var context: Context, var cartList: List<Cart>, var onGoToDetailsButtonClickListener: OnGoToDetailsButtonClickListener): RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.orders_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = cartList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.confirmationId.setText("Confirmation Id: " + cartList[position].cartUniqueId)
        holder.totalPrice.setText("Total Price: " + cartList[position].totalPrice.toString() + "TL")
        holder.status.setText(cartList[position].status)

        holder.goToDetails.setOnClickListener {
            onGoToDetailsButtonClickListener.onDetailButtonClick(cartList[position].cartUniqueId)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val confirmationId = view.findViewById<TextView>(R.id.confirmation_id)
        val totalPrice = view.findViewById<TextView>(R.id.total_price)
        val goToDetails = view.findViewById<MaterialButton>(R.id.go_to_details)
        val status = view.findViewById<MaterialTextView>(R.id.status)
    }

    interface OnGoToDetailsButtonClickListener{
        fun onDetailButtonClick(confirmationId: String)
    }

}