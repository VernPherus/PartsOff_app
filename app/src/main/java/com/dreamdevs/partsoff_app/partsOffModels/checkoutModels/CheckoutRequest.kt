package com.dreamdevs.partsoff_app.partsOffModels.checkoutModels

import com.google.gson.annotations.SerializedName

data class CheckoutRequest(
    @SerializedName("address") val address: String,
    @SerializedName("barangay") val barangay: String,
    @SerializedName("city") val city: String,
    @SerializedName("email") val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("grand_total") val grandTotal: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("mobile") val mobile: String,
    @SerializedName("order_items") val orderItems: List<OrderItem>,
    @SerializedName("province") val province: String,
    @SerializedName("subtotal") val subtotal: String,
    @SerializedName("zip") val zip: String
)