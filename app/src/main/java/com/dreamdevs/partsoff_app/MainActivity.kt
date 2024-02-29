package com.dreamdevs.partsoff_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dreamdevs.partsoff_app.databinding.ActivityMainBinding
import com.dreamdevs.partsoff_app.partsOffApi.RetrofitClient
import com.dreamdevs.partsoff_app.partsOffModels.productModels.Products
import com.dreamdevs.partsoff_app.partsOffModels.productModels.ProductsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var newRecyclerview : RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private var newArrayList: ArrayList<Products> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newRecyclerview = findViewById(R.id.productsRecycler)
        newRecyclerview.layoutManager = LinearLayoutManager(this)
        newRecyclerview.setHasFixedSize(true)

        newArrayList = arrayListOf<Products>()

        productAdapter = ProductAdapter(newArrayList)
        newRecyclerview.adapter = productAdapter

        fetchProducts()
    }

    private fun fetchProducts() {
        RetrofitClient.authService.getProducts().enqueue(object : Callback<List<ProductsData>> {
            override fun onResponse(call: Call<List<ProductsData>>, response: Response<List<ProductsData>>) {
                if (response.isSuccessful) {
                    val productList = response.body()
                    productList?.let {
                        updateRecyclerView(productList)
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<List<ProductsData>>, t: Throwable) {
                // Handle failure here
            }
        })
    }

    private fun updateRecyclerView(productList: List<ProductsData>) {
        newArrayList.clear()
        productList.forEach { productData ->
            val product = Products(productData.title, productData.description ?: "", productData.price.toInt(), productData.qty.toInt())
            newArrayList.add(product)
        }
        productAdapter.notifyDataSetChanged()
    }

}
