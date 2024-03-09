package com.dreamdevs.partsoff_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dreamdevs.partsoff_app.databinding.ActivityProductViewBinding

class ProductView : AppCompatActivity() {

    private lateinit var binding : ActivityProductViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle : Bundle?= intent.extras
        val title = bundle!!.getString("title")

        binding.productTitle.text = title
    }
}