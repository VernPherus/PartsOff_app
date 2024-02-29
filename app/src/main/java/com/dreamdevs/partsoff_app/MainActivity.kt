package com.dreamdevs.partsoff_app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dreamdevs.partsoff_app.databinding.ActivityMainBinding
import com.dreamdevs.partsoff_app.storage.SharedPrefManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Simulate an item for demonstration purposes
        val item = Item("Item 1", "$10", "gpu_gtx1660super")

        // Setup a click listener for the buy now button

        binding.buyNowButton1.setOnClickListener {
            // Add items on cart
            Toast.makeText(this, "${item.name} added to cart!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    data class Item(val name: String, val price: String, val imageUrl: String)

    override fun onStart() {
        super.onStart()

        if(!SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
