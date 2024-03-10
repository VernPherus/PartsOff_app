package com.dreamdevs.partsoff_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dreamdevs.partsoff_app.databinding.ActivityUserProfileBinding
import com.dreamdevs.partsoff_app.storage.SharedPrefManager

class UserProfile : AppCompatActivity() {

    private lateinit var binding : ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            startActivity(Intent(this@UserProfile, MainActivity::class.java))
        }

        binding.logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun logout(){
        SharedPrefManager.getInstance(applicationContext).clear()
        startActivity(Intent(this@UserProfile, LoginActivity::class.java))
    }
}