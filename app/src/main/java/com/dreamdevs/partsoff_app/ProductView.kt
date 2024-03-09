package com.dreamdevs.partsoff_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dreamdevs.partsoff_app.databinding.ActivityProductViewBinding

class ProductView : AppCompatActivity() {

    private lateinit var binding : ActivityProductViewBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle : Bundle?= intent.extras
        val title = bundle!!.getString("title")
        val desc = bundle.getString("description")
        val price = bundle.getString("price")
        val qty = bundle.getString("qty")

        binding.productTitle.text = title
        binding.productDesc.text = desc
        binding.itemPrice.text = "₱ $price"
        binding.itemQty.text = qty
    }
}