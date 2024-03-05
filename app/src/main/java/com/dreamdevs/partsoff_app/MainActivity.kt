package com.dreamdevs.partsoff_app

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dreamdevs.partsoff_app.databinding.ActivityMainBinding
import com.dreamdevs.partsoff_app.partsOffApi.RetrofitClient
import com.dreamdevs.partsoff_app.partsOffModels.productModels.Products
import com.dreamdevs.partsoff_app.partsOffModels.productModels.ProductsData
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter
    private var productList: ArrayList<Products> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchProducts()
        setupSearch()
    }

    private fun setupRecyclerView() {
        binding.productsRecycler.layoutManager = LinearLayoutManager(this)
        binding.productsRecycler.setHasFixedSize(true)
        productAdapter = ProductAdapter(productList)
        binding.productsRecycler.adapter = productAdapter
    }

    private fun fetchProducts() {
        RetrofitClient.authService.getProducts().enqueue(object : Callback<List<ProductsData>> {
            override fun onResponse(call: Call<List<ProductsData>>, response: Response<List<ProductsData>>) {
                if (response.isSuccessful) {
                    val productListData = response.body() ?: return

                    updateRecyclerView(productListData)
                } else {
                    Log.e("FetchProducts", "Unsuccessful response: ${response.errorBody()?.string()}")
                    Toast.makeText(this@MainActivity, "Failed to fetch products. Please try again.", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<ProductsData>>, t: Throwable) {
                Log.e("FetchProducts", "Failed to fetch products", t)
                Toast.makeText(this@MainActivity, "An error occurred while fetching products. Please check your internet connection and try again.", Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun updateRecyclerView(productDataList: List<ProductsData>) {
        productList.clear()
        productDataList.forEach { productData ->
            productList.add(Products(productData.title,
                productData.description, productData.price.toInt(), productData.qty.toInt()))
        }
        productAdapter.notifyDataSetChanged()
    }

    private fun setupSearch() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                productAdapter.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}
