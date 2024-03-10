package com.dreamdevs.partsoff_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dreamdevs.partsoff_app.databinding.ActivityProductViewBinding

class ProductView : AppCompatActivity() {

    private lateinit var binding: ActivityProductViewBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val id = bundle!!.getInt("id")
        val title = bundle.getString("title")
        val desc = bundle.getString("description")
        val price = bundle.getString("price")
        val qty = bundle.getString("qty")
        val imageUrl = bundle.getString("image")


        binding.productTitle.text = title
        binding.productDesc.text = desc
        binding.itemPrice.text = "₱ $price"
        binding.itemQty.text = qty

        imageUrl?.let {
            Glide.with(this@ProductView)
                .load("http://64.23.185.162/uploads/product/large/$it")
                .into(binding.productDisp)
        }

        binding.backButton.setOnClickListener {
            startActivity(Intent(this@ProductView, MainActivity::class.java))
        }
    }
}

