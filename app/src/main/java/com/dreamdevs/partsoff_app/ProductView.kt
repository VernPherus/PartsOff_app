package com.dreamdevs.partsoff_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dreamdevs.partsoff_app.databinding.ActivityProductViewBinding

class ProductView : AppCompatActivity() {

    private lateinit var binding: ActivityProductViewBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val productId = bundle!!.getInt("id")
        val title = bundle.getString("title")
        val desc = bundle.getString("description")
        val price = bundle.getString("price")
        val qty = bundle.getString("qty")

        binding.productTitle.text = title
        binding.productDesc.text = desc
        binding.itemPrice.text = "₱ $price"
        binding.itemQty.text = qty

        binding.backButton.setOnClickListener {
            val intent = Intent(this@ProductView, MainActivity::class.java)
            startActivity(intent)
        }

        binding.addToCart.setOnClickListener {
            // Get the existing cart data from SharedPreferences
            val sharedPreferences = getSharedPreferences("cart", MODE_PRIVATE)
            val cartId = sharedPreferences.getString("id", "")
            val cartTitle = sharedPreferences.getString("title", "")
            val cartDesc = sharedPreferences.getString("description", "")
            val cartPrice = sharedPreferences.getString("price", "")

            // Get the current product details
            val currentId = productId
            val currentTitle = title
            val currentDesc = desc
            val currentPrice = price

            // Concatenate the current product details with existing cart data
            val updatedCartId = if (cartId.isNullOrEmpty()) currentId.toString() else "$cartId, $currentId"
            val updatedCartTitle = if (cartTitle.isNullOrEmpty()) currentTitle else "$cartTitle, $currentTitle"
            val updatedCartDesc = if (cartDesc.isNullOrEmpty()) currentDesc else "$cartDesc, $currentDesc"
            val updatedCartPrice = if (cartPrice.isNullOrEmpty()) currentPrice else "$cartPrice, $currentPrice"

            // Save the updated cart data to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("id", updatedCartId)
            editor.putString("title", updatedCartTitle)
            editor.putString("description", updatedCartDesc)
            editor.putString("price", updatedCartPrice)
            editor.apply()

            // Navigate to the Cart activity
            val intent = Intent(this@ProductView, CartActivity::class.java)
            startActivity(intent)
        }
    }
}
