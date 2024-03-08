package com.dreamdevs.partsoff_app.storage

import android.annotation.SuppressLint
import android.content.Context
import com.dreamdevs.partsoff_app.partsOffModels.authModels.LoginRequest

class SharedPrefManager private constructor(private val mCtx: Context) {

    // Checking for a specific login flag instead of just userEmail existence
    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean("isLoggedIn", false)
        }

    val user: LoginRequest
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return LoginRequest(
                sharedPreferences.getString("userEmail", "") ?: "",
                sharedPreferences.getString("userPassword", "") ?: ""
            )
        }

    fun saveUser(user: LoginRequest) {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("userEmail", user.email)
        editor.putString("userPassword", user.password)
        editor.putBoolean("isLoggedIn", true) // Explicitly mark the user as logged in

        editor.apply()
    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val SHARED_PREF_NAME = "userPrefs"
        @SuppressLint("StaticFieldLeak")
        private var mInstance: SharedPrefManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }
}
