package com.dreamdevs.partsoff_app.partsOffModels.productModels

data class Product(
    val description: String,
    val id: Int,
    val price: Int,
    val product_images: List<ProductImage>,
    val qty: Int,
    val title: String
)