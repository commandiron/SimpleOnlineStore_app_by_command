package com.demirli.a51simpleonlinestore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    var product_id: Int = 0,
    var product_name: String,
    var product_thumbnail_url: String,
    var product_short_description: String,
    var product_long_description: String,
    var product_price: Float,
    var quantity: Int
) {
}