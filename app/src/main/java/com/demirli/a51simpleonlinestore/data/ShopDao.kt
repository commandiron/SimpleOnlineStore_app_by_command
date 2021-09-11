package com.demirli.a51simpleonlinestore.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demirli.a51simpleonlinestore.model.Cart
import com.demirli.a51simpleonlinestore.model.CartDetail
import com.demirli.a51simpleonlinestore.model.Product

@Dao
interface ShopDao {

    @Query("SELECT * FROM product")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM product WHERE product_id= :productId")
    fun getSingleProduct(productId: Int): LiveData<Product>

    @Query("SELECT * FROM cart WHERE userId= :userId")
    fun getAllCarts(userId: Int): LiveData<List<Cart>>

    @Query("SELECT * FROM cartdetail WHERE cartUniqueId= :cartUniqueId")
    fun getAllCartDetails(cartUniqueId: String): LiveData<List<CartDetail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCart(cart: Cart): Long

    @Query("DELETE FROM cart WHERE cartUniqueId= :cartUniqueId")
    fun deleteCart(cartUniqueId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCartDetail(cartDetail: CartDetail)

}
