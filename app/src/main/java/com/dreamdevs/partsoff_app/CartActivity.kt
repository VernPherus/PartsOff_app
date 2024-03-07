package com.dreamdevs.partsoff_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dreamdevs.partsoff_app.partsOffApi.RetrofitClient
import com.dreamdevs.partsoff_app.partsOffModels.productModels.Products
import retrofit2.Call

class CartActivity : AppCompatActivity() {

    private lateinit var cartItemsRecyclerView: RecyclerView
    private lateinit var checkoutButton: Button
    private var cartItems: List<Products> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        initializeUI()
        loadProducts()
        setupCheckoutButton()
    }

    private fun initializeUI() {
        cartItemsRecyclerView = findViewById(R.id.cart_items_recyclerview)
        checkoutButton = findViewById(R.id.checkout_button)

        cartItemsRecyclerView.layoutManager = LinearLayoutManager(this)
        cartItemsRecyclerView.adapter = ProductAdapter(cartItems)
    }

    private fun loadProducts() {
        RetrofitClient.authService.getProducts().enqueue()
    }

    private fun updateRecyclerView() {
        (cartItemsRecyclerView.adapter as? ProductAdapter)?.apply {
            updateData()
        }
    }

    private fun updateData() {
        TODO("Not yet implemented")
    }

    private fun setupCheckoutButton() {
        checkoutButton.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        }
    }
}

private fun <T> Call<T>.enqueue() {

}

