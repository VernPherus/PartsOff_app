package com.dreamdevs.partsoff_app

import android.net.http.HttpException
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dreamdevs.partsoff_app.databinding.ActivityMainBinding
import com.dreamdevs.partsoff_app.partsOffApi.RetrofitClient
import java.io.IOException

const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var productAdapter : ProductAdapter

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitClient.authService.getProducts()
            }catch (e: IOException){
                Log.e(TAG, "IOException, no internet connection")
                binding.progressBar.isVisible=false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(TAG, "HttpException, Unexpected response")
                binding.progressBar.isVisible=false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null){
                productAdapter.product = response.body()!!
            }else {
                Log.e(TAG, "Response not successful")
                return@launchWhenCreated
            }

            binding.progressBar.isVisible=false

        }
    }

    private fun setupRecyclerView() = binding.productsRecycler.apply {
        productAdapter = ProductAdapter()
        adapter = productAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

}
