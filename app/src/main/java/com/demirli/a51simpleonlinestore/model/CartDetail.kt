package com.demirli.a51simpleonlinestore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cartdetail")
data class CartDetail(

    @PrimaryKey(autoGenerate = true)
    var cartDetailId: Int = 0,
    var cartUniqueId: String,
    var product_id:Int,
    var quantity: Int
){
}