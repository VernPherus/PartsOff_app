package com.dreamdevs.partsoff_app.partsOffModels.checkoutModels

import org.json.JSONObject

data class OrderItem(val id: Int, val qty: Int) {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getInt("product_id"),
        jsonObject.getInt("qty")
    )
}