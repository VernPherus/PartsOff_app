package com.dreamdevs.partsoff_app

import android.net.http.HttpException
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dreamdevs.partsoff_app.Api.RetrofitInstance
import com.dreamdevs.partsoff_app.databinding.ActivityMainBinding
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding : ActivityMainBinding

    private lateinit var userAdapter: UserAdapter

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getUsers()
            } catch (e: IOException){
                Log.e(TAG, "IOException, you might not have internet connection.")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(TAG, "HttpException, unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                userAdapter.users = response.body()!!
            } else {
                Log.e(TAG, "Response not successful")
            }
            binding.progressBar.isVisible = false
        }
    }

    private fun setupRecyclerView() = binding.rvUsers.apply {
        userAdapter = UserAdapter()
        adapter = userAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

}