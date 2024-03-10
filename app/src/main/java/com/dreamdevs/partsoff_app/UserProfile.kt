package com.dreamdevs.partsoff_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dreamdevs.partsoff_app.databinding.ActivityUserProfileBinding
import com.dreamdevs.partsoff_app.storage.SharedPrefManager

class UserProfile : AppCompatActivity() {

    private lateinit var binding : ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            val intent = Intent(this@UserProfile, MainActivity::class.java)
            startActivity(intent)
        }

        binding.logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun logout(){
        SharedPrefManager.getInstance(applicationContext).clear()
        val intent = Intent(this@UserProfile, LoginActivity::class.java)
        startActivity(intent)
    }
}