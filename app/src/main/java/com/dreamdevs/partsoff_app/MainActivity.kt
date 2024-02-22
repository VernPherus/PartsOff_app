package com.dreamdevs.partsoff_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dreamdevs.partsoff_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        data class Item(val name: String, val price: String, val imageUrl: String)
        listOf(
            Item("Item 1", "$10", "gpu_gtx1660super"),
            Item("Item 2", "$20", "gpu_rtx3060"),
            Item("Item 2", "$20", "gpu_rtx4080")
        )


    }
}
