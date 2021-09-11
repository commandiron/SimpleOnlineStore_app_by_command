package com.demirli.a51simpleonlinestore.ui.fragment.cart

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demirli.a51simpleonlinestore.R
import com.demirli.a51simpleonlinestore.model.Cart
import com.demirli.a51simpleonlinestore.model.CartDetail
import com.demirli.a51simpleonlinestore.model.Product
import com.demirli.a51simpleonlinestore.ui.activity.MainViewModel
import com.demirli.a51simpleonlinestore.ui.fragment.orders.OrdersFragment
import com.demirli.a51simpleonlinestore.ui.fragment.products.ProductsFragment
import kotlinx.android.synthetic.main.fragment_cart.*
import java.util.ArrayList

private lateinit var adapter: CartAdapter
private lateinit var viewModel: MainViewModel

private lateinit var productListOfCart: ArrayList<Product>
private lateinit var cartUniqueId: String
private var totalPrice: Float? = null

class CartFragment : Fragment() {

    private var cartFragmentListener: CartFragmentListener? = null

    companion object {
        fun newInstance(productListOfCart: ArrayList<Product>?, cartUniqueId: String?, totalPrice: Float?): CartFragment{
            val args = Bundle()
            try {
                args.putSerializable("productListOfCart", productListOfCart)
                args.putString("cartUniqueId", cartUniqueId)
                args.putFloat("totalPrice", totalPrice!!)
            }catch (e: Exception){
                e.printStackTrace()
            }


            val fragment = CartFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_cart, container, false)

        try {
            productListOfCart = arguments?.getSerializable("productListOfCart") as ArrayList<Product>
            cartUniqueId = arguments?.getString("cartUniqueId")!!
            totalPrice = arguments?.getFloat("totalPrice")
        }catch (e: Exception){
            e.printStackTrace()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try{
            cartFragmentListener = context as CartFragmentListener
        }catch (e: ClassCastException){
            throw java.lang.ClassCastException(context.toString() + "CastFragmentListener implement edilmeli.")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(MainViewModel::class.java)

        cart_recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        adapter = CartAdapter(activity!!.applicationContext, productListOfCart)
        cart_recyclerView.adapter = adapter

        total_price.setText("Total Price: " + totalPrice + "TL")

        place_order.setOnClickListener {
            for(i in productListOfCart){
                viewModel.addCartDetail(CartDetail(cartUniqueId = cartUniqueId, product_id = i.product_id, quantity = i.quantity))
            }

            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,
                OrdersFragment.newInstance(cartUniqueId)).addToBackStack("fragmentStack").commit()
        }

        cancel_order.setOnClickListener {

            viewModel.deleteCart(cartUniqueId)

            cartFragmentListener?.cancelOrderFromCartFragment()

            productListOfCart.clear()
            cartUniqueId = ""
            totalPrice = 0f

            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container,
                ProductsFragment()).addToBackStack("fragmentStack").commit()
        }
    }
}
