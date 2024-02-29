package com.dreamdevs.partsoff_app.partsOffModels.productModels

data class ProductImage(
    val created_at: String,
    val id: Int,
    val image: String,
    val product_id: Int,
    val sort_order: Any,
    val updated_at: String
)