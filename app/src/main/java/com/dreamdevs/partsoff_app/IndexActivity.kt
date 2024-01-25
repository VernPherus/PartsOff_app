package com.dreamdevs.partsoff_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dreamdevs.partsoff_app.databinding.ActivityIndexBinding

class IndexActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIndexBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIndexBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}