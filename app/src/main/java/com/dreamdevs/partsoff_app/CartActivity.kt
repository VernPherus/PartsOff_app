package com.dreamdevs.partsoff_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val checkoutButton: Button = findViewById(R.id.checkout_button)
        checkoutButton.setOnClickListener {

        }
    }
}