package com.demirli.a51simpleonlinestore.ui.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.demirli.a51simpleonlinestore.model.Cart
import com.demirli.a51simpleonlinestore.model.CartDetail
import com.demirli.a51simpleonlinestore.model.Product

class MainViewModel(application: Application): AndroidViewModel(application){
    private val repository: MainRepository by lazy { MainRepository(application)}

    fun getAllProducts() = repository.getAllProducts()

    fun getSingleProduct(productId: Int) = repository.getSingleProduct(productId)

    fun getAllCarts(userId: Int) = repository.getAllCarts(userId)

    fun getAllCartDetails(cartUniqueId: String) = repository.getAllCartDetails(cartUniqueId)

    fun addCart(cart: Cart) = repository.addCart(cart)

    fun deleteCart(cartUniqueId: String) = repository.deleteCart(cartUniqueId)

    fun addCartDetail(cartDetail: CartDetail) = repository.addCartDetail(cartDetail)

}