package com.dreamdevs.partsoff_app.partsOffClasses.productClasses

data class Product(
    val category_id: Int,
    val created_at: String,
    val description: Any,
    val id: Int,
    val is_featured: String,
    val price: Int,
    val product_detail: Any,
    val product_images: List<ProductImage>,
    val qty: Int,
    val sku: String,
    val slug: String,
    val status: Int,
    val title: String,
    val track_qty: String,
    val updated_at: String
)