package com.dreamdevs.partsoff_app.partsOffModels.checkoutModels

import com.google.gson.annotations.SerializedName

data class OrderItem(
    @SerializedName("product_id") val productId: Int,
    @SerializedName("qty") val qty: Int
)