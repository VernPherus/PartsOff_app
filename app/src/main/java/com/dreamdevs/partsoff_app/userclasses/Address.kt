package com.dreamdevs.partsoff_app.userclasses

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)