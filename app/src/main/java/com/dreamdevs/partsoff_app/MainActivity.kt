package com.dreamdevs.partsoff_app

import android.R.attr.label
import android.R.attr.text
import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dreamdevs.partsoff_app.account.LoginActivity
import com.dreamdevs.partsoff_app.databinding.ActivityMainBinding
import com.dreamdevs.partsoff_app.partsOffApi.RetrofitClient
import com.dreamdevs.partsoff_app.partsOffModels.productModels.ProductsData
import com.dreamdevs.partsoff_app.storage.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter
    private var productList: ArrayList<ProductsData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!SharedPrefManager.getInstance(applicationContext).isLoggedIn) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        setupRecyclerView()
        fetchProducts()
        setupSearch()
        popupMenu()

        productAdapter.setOnItemClickListener(object : ProductAdapter.OnItemListener {
            override fun onItemClick(position: Int) {
                val clickedProduct = productAdapter.productListFiltered[position]

                val intent = Intent(this@MainActivity, ProductView::class.java).apply {
                    putExtra("id", clickedProduct.id)
                    putExtra("title", clickedProduct.title)
                    putExtra("description", clickedProduct.description.toString())
                    putExtra("price", clickedProduct.price.toString())
                    putExtra("qty", clickedProduct.qty.toString())
                    putExtra("image", clickedProduct.productImages.firstOrNull()?.image)
                }
                startActivity(intent)

            }
        })

        binding.cartButton.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        setupRefreshButton()

    }

    private fun scrollToTop() {
        binding.productsRecycler.scrollToPosition(0)
    }

    private fun setupRefreshButton() {
        binding.refreshButton.setOnClickListener {
            // Handle refresh button click
            fetchProducts()
            scrollToTop()
        }
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
            val quantity = productData.qty.toInt()
            if (quantity > 0) {
                productList.add(
                    ProductsData(
                        productData.id,
                        productData.title,
                        productData.description,
                        productData.price.toInt(),
                        quantity,
                        productData.productImages,

                    )
                )
            }
        }

        productList.reverse()

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

    @SuppressLint("DiscouragedPrivateApi")
    private fun popupMenu(){
        val popupMenu = PopupMenu(applicationContext, binding.profileButton)
        popupMenu.inflate(R.menu.profile_menu)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.logout_action -> {
                    Toast.makeText(applicationContext, "Successfully Logged out", Toast.LENGTH_SHORT).show()
                    SharedPrefManager.getInstance(applicationContext).clear()
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    true
                }
                else -> true
            }
        }
        binding.profileButton.setOnClickListener {

            try {
                val popup = PopupMenu::class.java.getDeclaredField("mPopup")
                popup.isAccessible = true
                val menu = popup.get(popupMenu)
                menu.javaClass
                    .getDeclaredMethod("setForcedShowIcon", Boolean::class.java)
                    .invoke(menu, true)
            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                popupMenu.show()
            }
            true
        }
    }


}
