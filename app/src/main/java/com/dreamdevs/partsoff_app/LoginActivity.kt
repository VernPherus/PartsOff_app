package com.dreamdevs.partsoff_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dreamdevs.partsoff_app.databinding.ActivityLoginBinding
import android.content.Intent
import com.dreamdevs.partsoff_app.partsOffApi.RetrofitClient
import com.dreamdevs.partsoff_app.partsOffModels.authModels.LoginRequest
import com.dreamdevs.partsoff_app.storage.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener(View.OnClickListener {

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (email.isEmpty()){
                binding.email.error = "Email Required"
                binding.email.requestFocus()
                return@OnClickListener
            }

            if (password.isEmpty()){
                binding.password.error = "Password Required"
                binding.password.requestFocus()
                return@OnClickListener
            }

            performLogin(email, password)

        })


        binding.signupButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }

    private fun performLogin(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        val call = RetrofitClient.authService.login(email, password)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Handle successful login, e.g., navigate to main activity
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // Handle login error, e.g., display error message
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle network error, e.g., display error message
            }
        })
    }
}