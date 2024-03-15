package com.dreamdevs.partsoff_app.partsOffModels.checkoutModels

import com.google.gson.annotations.SerializedName

data class CheckoutRequest(
    val address: String,
    val barangay: String,
    val city: String,
    val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("grand_total") val grandTotal: Double,
    @SerializedName("last_name") val lastName: String,
    val mobile: String,
    @SerializedName("order_items") val orderItems: List<OrderItem>,
    val province: Int,
    val subtotal: Double,
    val zip: String
)