package com.dreamdevs.partsoff_app.partsOffModels.productModels

import com.google.gson.annotations.SerializedName

data class ProductsData(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Int, // Change type to Int for price
    @SerializedName("qty") val qty: Int, // Change type to Int for qty
    @SerializedName("product_images") val productImages: List<ProductImage>,
)

data class ProductImage(
    @SerializedName("image")val image: String
)
