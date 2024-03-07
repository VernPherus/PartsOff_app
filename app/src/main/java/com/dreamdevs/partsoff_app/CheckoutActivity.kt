package com.dreamdevs.partsoff_app

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val buttonConfirmOrder: Button = findViewById(R.id.buttonConfirmOrder)
        buttonConfirmOrder.setOnClickListener {
            Toast.makeText(this, "Order Confirmed!", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
