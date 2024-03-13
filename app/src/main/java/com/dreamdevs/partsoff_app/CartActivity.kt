package com.dreamdevs.partsoff_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        binding = ActivityCartBinding.inflate(layoutInflater)
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

        // Create an instance of ProductAdapter and set the click listener
        val productAdapter = ProductAdapter(cartItems)
        productAdapter.setOnItemClickListener(object : ProductAdapter.OnItemListener {
            override fun onItemClick(position: Int) {
                // Handle item click action
                // You can implement the desired behavior when an item is clicked
            }
        })
        productAdapter.setOnItemLongPressListener(object : ProductAdapter.OnItemLongPressListener {
            override fun onItemLongPressed(position: Int) {
                // Call the method to delete item from cart
                deleteCartItem(position)
            }
        })

        // Set the adapter to the RecyclerView
        cartItemsRecyclerView.adapter = productAdapter
    }

    private fun initializeUI() {
        cartItemsRecyclerView = binding.cartItemsRecyclerview // Update to use binding
        checkoutButton = binding.checkoutButton // Update to use binding

        cartItemsRecyclerView.layoutManager = LinearLayoutManager(this)
        cartItemsRecyclerView.adapter = ProductAdapter(cartItems)
    }

    @SuppressLint("SetTextI18n")
    private fun loadProducts() {
        val sharedPreferences = SharedPrefManager.getInstance(this)
        cartItems = sharedPreferences.getCartItems()

        // Check if the cart is empty
        if (cartItems.isEmpty()) {
            // If the cart is empty, show the TextView and hide the RecyclerView
            binding.cartItemsRecyclerview.visibility = View.GONE
            binding.emptyCartMessage.visibility = View.VISIBLE
        } else {
            // If the cart is not empty, show the RecyclerView and hide the TextView
            binding.cartItemsRecyclerview.visibility = View.VISIBLE
            binding.emptyCartMessage.visibility = View.GONE
        }

        // Calculate the subtotal price
        var subtotalPrice = 0.00
        for (product in cartItems) {
            subtotalPrice += product.price
        }

        var totalQty = 0
        for (product in cartItems) {
            totalQty += product.qty
        }
        var shipping = if (cartItems.isEmpty()) {
            0.0
        } else {
            35.00
        }
        var totalPrice = 0.00

        totalPrice = subtotalPrice + shipping

        // Display the total price in the TextView
        binding.totalQtyTextView.text = "Total QTY: $totalQty"
        binding.ShippingTextView.text = "Shipping Fee: ₱$shipping"
        binding.subtotalPriceTextView.text = "Subtotal Price: ₱$subtotalPrice"
        binding.totalPriceTextView.text = "Total Price: ₱$totalPrice"

        // Set the adapter to the RecyclerView
        cartItemsRecyclerView.adapter = ProductAdapter(cartItems)
    }

    private fun setupCheckoutButton() {
        checkoutButton.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteCartItem(position: Int) {
        // Get the item to be deleted
        val deletedItem = cartItems[position]

        // Build and show a confirmation dialog
        AlertDialog.Builder(this)
            .setTitle("Confirm Deletion")
            .setMessage("Are you sure you want to delete this item from your cart?")
            .setPositiveButton("Yes") { _, _ ->
                // User confirmed, proceed with deletion
                cartItems = cartItems.filterIndexed { index, _ -> index != position }

                // Update shared preferences
                val sharedPreferences = SharedPrefManager.getInstance(this)
                sharedPreferences.removeCartItem(deletedItem)

                // Reload the products from SharedPreferences
                cartItems = sharedPreferences.getCartItems()

                // Update the UI
                loadProducts()
            }
            .setNegativeButton("No", null) // User canceled, do nothing
            .show()
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
