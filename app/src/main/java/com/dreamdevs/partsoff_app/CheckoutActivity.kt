package com.dreamdevs.partsoff_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dreamdevs.partsoff_app.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCheckoutBinding

    private lateinit var qty : String
    private lateinit var shipping : String
    private lateinit var subTotalPrice : String
    private lateinit var totalPrice : String


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        qty = bundle!!.getString("qty").toString()
        shipping = bundle.getString("shipping").toString()
        subTotalPrice = bundle.getString("subTotalPrice").toString()
        totalPrice = bundle.getString("totalPrice").toString()

        displayTotal()

        val buttonConfirmOrder: Button = findViewById(R.id.buttonConfirmOrder)
        buttonConfirmOrder.setOnClickListener {
            Toast.makeText(this, "Order Confirmed!", Toast.LENGTH_LONG).show()
            finish()
        }

        binding.backButton.setOnClickListener {
            startActivity(Intent(this@CheckoutActivity, MainActivity::class.java))
        }

    }


    private fun displayTotal(){
        binding.totalQtyTextView.text = "Total Quantity : $qty"
        binding.ShippingTextView.text = "Shipping Fee: ₱$shipping"
        binding.subtotalPriceTextView.text = "Subtotal Price: ₱$subTotalPrice"
        binding.totalPriceTextView.text = "₱$totalPrice"
    }

}
