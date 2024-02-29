package com.dreamdevs.partsoff_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dreamdevs.partsoff_app.databinding.ActivitySignUpBinding
import com.dreamdevs.partsoff_app.partsOffModels.authModels.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { super.onBackPressed() }

        binding.signupButton.setOnClickListener {

            val name = binding.name.text.toString().trim()
            val email = binding.email.text.toString().trim()
            val phoneNumber = binding.phoneNumber.text.toString().trim()
            val password = binding.password.text.toString().trim()
            val passwordConfirmation = binding.reEnterPassword.text.toString().trim()

            if (name.isEmpty())
            {
                binding.name.error = "Name required"
                binding.name.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty())
            {
                binding.email.error = "Email required"
                binding.email.requestFocus()
                return@setOnClickListener
            }

            if (phoneNumber.isEmpty())
            {
                binding.phoneNumber.error = "Phone number required"
                binding.phoneNumber.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty())
            {
                binding.password.error = "Password required"
                binding.password.requestFocus()
                return@setOnClickListener
            }

            if (passwordConfirmation.isEmpty() || passwordConfirmation!=password)
            {
                binding.reEnterPassword.error = "Incorrect password"
                binding.reEnterPassword.requestFocus()
                return@setOnClickListener
            }



        }
    }

    @Deprecated("Deprecated in Java",
        ReplaceWith("super.onBackPressed()", "androidx.appcompat.app.AppCompatActivity")
    )
    override fun onBackPressed() {
        super.onBackPressed()
    }
}
