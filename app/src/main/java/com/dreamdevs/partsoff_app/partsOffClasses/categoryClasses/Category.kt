package com.dreamdevs.partsoff_app.partsOffClasses.categoryClasses

data class Category(
    val created_at: String,
    val id: Int,
    val image: Any,
    val name: String,
    val showHome: String,
    val slug: String,
    val status: Int,
    val updated_at: String
)