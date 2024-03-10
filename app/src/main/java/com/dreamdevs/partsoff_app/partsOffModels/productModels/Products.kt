package com.dreamdevs.partsoff_app.partsOffModels.productModels

data class Products(
    val id: Int,
    val title: String? = null,
    val description: String? = null,
    val price: Int? = null,
    val qty: Int? = null,
    val productImages: List<ProductsImage>? = null
)
data class ProductsImage(
    val id: Int,
    val productId: Int,
    val image: String

)