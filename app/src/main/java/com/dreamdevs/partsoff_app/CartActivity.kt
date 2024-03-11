package com.dreamdevs.partsoff_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dreamdevs.partsoff_app.account.LoginActivity
import com.dreamdevs.partsoff_app.databinding.ActivityCartBinding // Correct import
import com.dreamdevs.partsoff_app.partsOffModels.productModels.ProductsData
import com.dreamdevs.partsoff_app.storage.SharedPrefManager

class CartActivity : AppCompatActivity() {

    private lateinit var cartItemsRecyclerView: RecyclerView
    private lateinit var checkoutButton: Button
    private lateinit var binding : ActivityCartBinding
    private var cartItems: List<ProductsData> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater) // Correct inflation
        setContentView(binding.root)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            startActivity(Intent(this@CartActivity, MainActivity::class.java))
        }

        initializeUI()
        loadProducts()
        setupCheckoutButton()
        popupMenu()

        binding.backButton.setOnClickListener {
            startActivity(Intent(this@CartActivity, MainActivity::class.java))
        }

        binding.cartButton.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun initializeUI() {
        cartItemsRecyclerView = binding.cartItemsRecyclerview // Update to use binding
        checkoutButton = binding.checkoutButton // Update to use binding

        cartItemsRecyclerView.layoutManager = LinearLayoutManager(this)
        cartItemsRecyclerView.adapter = ProductAdapter(cartItems)
    }

    private fun loadProducts() {
        // Implement loading products if needed
    }

    private fun setupCheckoutButton() {
        checkoutButton.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("DiscouragedPrivateApi")
    private fun popupMenu() {
        val popupMenu = PopupMenu(applicationContext, binding.profileButton)
        popupMenu.inflate(R.menu.profile_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.logout_action -> {
                    Toast.makeText(applicationContext, "Successfully Logged out", Toast.LENGTH_SHORT).show()
                    SharedPrefManager.getInstance(applicationContext).clear()
                    startActivity(Intent(this@CartActivity, LoginActivity::class.java))
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
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                popupMenu.show()
            }
        }
    }
}
