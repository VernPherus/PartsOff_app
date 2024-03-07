package com.dreamdevs.partsoff_app.partsOffModels.productModels

import com.google.gson.annotations.SerializedName

data class ProductsData(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: String,
    @SerializedName("qty") val qty: String
)
