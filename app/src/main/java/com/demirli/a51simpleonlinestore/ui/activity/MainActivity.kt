package com.demirli.a51simpleonlinestore.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demirli.a51simpleonlinestore.R
import com.demirli.a51simpleonlinestore.model.Cart
import com.demirli.a51simpleonlinestore.model.Product
import com.demirli.a51simpleonlinestore.ui.fragment.cart.CartFragment
import com.demirli.a51simpleonlinestore.ui.fragment.cart.CartFragmentListener
import com.demirli.a51simpleonlinestore.ui.fragment.products.ProductsFragment
import com.demirli.a51simpleonlinestore.ui.fragment.products.ProductsFragmentListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(),
    ProductsFragmentListener, CartFragmentListener {

    private lateinit var viewModel: MainViewModel

    private var numberOfCartItem = 0
    private var productListOfCart = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = MainViewModel(application)

        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction().add(R.id.container,
            ProductsFragment()
        ).addToBackStack("fragmentStack").commit()

        go_to_cart_layout.setOnClickListener {

            val cartUniqueId = UUID.randomUUID().toString()
            var totalPrice = 0f

            productListOfCart.forEach{
                totalPrice += it.product_price * it.quantity
            }
            //UserId test için el ile verildi.
            viewModel.addCart(Cart(userId = 1, cartUniqueId = cartUniqueId, totalPrice = totalPrice, status = "Preparing"))

            supportFragmentManager.beginTransaction().replace(R.id.container,
                CartFragment.newInstance(productListOfCart, cartUniqueId, totalPrice)
            ).addToBackStack("fragmentStack").commit()


        }
    }

    override fun cartItemFromProductsFragment(product: Product) {
        numberOfCartItem++
        numberOf_cart_item.setText(numberOfCartItem.toString())
        productListOfCart.add(product)
    }

    override fun cancelOrderFromCartFragment() {
        numberOfCartItem = 0
        numberOf_cart_item.setText(numberOfCartItem.toString())
        productListOfCart.clear()
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if(count == 0){
            super.onBackPressed()
        }else{
            supportFragmentManager.popBackStack()
        }
        super.onBackPressed()
    }
}
