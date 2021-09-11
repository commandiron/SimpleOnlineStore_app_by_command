package com.demirli.a51simpleonlinestore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
class Cart(
    @PrimaryKey(autoGenerate = true)
    var cartId: Int = 0,
    var cartUniqueId: String,
    var userId: Int,
    var totalPrice: Float,
    var status: String

) {
}