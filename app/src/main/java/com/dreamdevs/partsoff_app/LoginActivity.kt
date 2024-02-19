package com.dreamdevs.partsoff_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.dreamdevs.partsoff_app.databinding.ActivityLoginBinding
import android.content.Intent

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var loginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener(View.OnClickListener {
            if (binding.email.text.toString() =="email" && binding.password.text.toString() == "1234"){
                Toast.makeText(this,"Login Succesful!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Login Failed", Toast.LENGTH_SHORT).show()
            }

        })
        binding.signupButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}