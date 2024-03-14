package com.dreamdevs.partsoff_app.partsOffModels.checkoutModels

import com.google.gson.annotations.SerializedName

data class CheckoutRequest(
    @SerializedName("address") val address: String,
    @SerializedName("barangay") val barangay: String,
    @SerializedName("city") val city: String,
    @SerializedName("email") val email: String,
    @SerializedName("first_name") val first_name: String,
    @SerializedName("grand_total") val grand_total: String,
    @SerializedName("id") val id: String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("mobile") val mobile: String,
    @SerializedName("order_items") val order_items: List<OrderItem>,
    @SerializedName("province") val province: String,
    @SerializedName("subtotal") val subtotal: String,
    @SerializedName("zip") val zip: String
)