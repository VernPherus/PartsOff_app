package com.dreamdevs.partsoff_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dreamdevs.partsoff_app.account.LoginActivity
import com.dreamdevs.partsoff_app.databinding.ActivityProductViewBinding
import com.dreamdevs.partsoff_app.storage.SharedPrefManager

class ProductView : AppCompatActivity() {

    private lateinit var binding: ActivityProductViewBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val id = bundle!!.getInt("id")
        val title = bundle.getString("title")
        val desc = bundle.getString("description")
        val price = bundle.getString("price")
        val qty = bundle.getString("qty")
        val imageUrl = bundle.getString("image")


        binding.productTitle.text = title
        binding.productDesc.text = desc
        binding.itemPrice.text = "₱ $price"
        binding.itemQty.text = qty

        imageUrl?.let {
            Glide.with(this@ProductView)
                .load("http://64.23.185.162/uploads/product/large/$it")
                .into(binding.productDisp)
        }

        binding.backButton.setOnClickListener {
            startActivity(Intent(this@ProductView, MainActivity::class.java))
        }

        popupMenu()
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
                    startActivity(Intent(this@ProductView, LoginActivity::class.java))
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

