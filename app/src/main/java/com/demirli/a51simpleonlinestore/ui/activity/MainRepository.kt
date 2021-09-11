package com.demirli.a51simpleonlinestore.ui.activity

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.demirli.a51simpleonlinestore.data.ShopDao
import com.demirli.a51simpleonlinestore.data.ShopDb
import com.demirli.a51simpleonlinestore.model.Cart
import com.demirli.a51simpleonlinestore.model.CartDetail
import com.demirli.a51simpleonlinestore.model.Product

class MainRepository(context: Context) {

    private val db by lazy{ShopDb.getInstance(context)}
    private val dao by lazy{db.shopDao()}

    fun getAllProducts(): LiveData<List<Product>> = dao.getAllProducts()

    fun getSingleProduct(productId: Int): LiveData<Product> = dao.getSingleProduct(productId)

    fun getAllCarts(userId: Int):LiveData<List<Cart>> = dao.getAllCarts(userId)

    fun getAllCartDetails(cartUniqueId: String): LiveData<List<CartDetail>> = dao.getAllCartDetails(cartUniqueId)

    fun addCart(cart: Cart) = AddCartAsyncTask(dao).execute(cart)

    fun deleteCart(cartUniqueId: String) = DeleteCartAsyncTask(dao).execute(cartUniqueId)

    fun addCartDetail(cartDetail: CartDetail) = AddCartDetailAsyncTask(dao).execute(cartDetail)

    private class AddCartAsyncTask(val dao: ShopDao): AsyncTask<Cart, Void, Void>(){
        override fun doInBackground(vararg params: Cart?): Void? {
            dao.addCart(params[0]!!)
            return null
        }
    }

    private class DeleteCartAsyncTask(val dao: ShopDao): AsyncTask<String, Void, Void>(){
        override fun doInBackground(vararg params: String?): Void? {
            dao.deleteCart(params[0]!!)
            return null
        }
    }

    private class AddCartDetailAsyncTask(val dao: ShopDao): AsyncTask<CartDetail, Void, Void>(){
        override fun doInBackground(vararg params: CartDetail?): Void? {
            dao.addCartDetail(params[0]!!)
            return null
        }
    }

}