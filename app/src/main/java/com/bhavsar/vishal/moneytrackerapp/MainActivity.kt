package com.bhavsar.vishal.moneytrackerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.bhavsar.vishal.moneytrackerapp.data.UserPreferences
import com.bhavsar.vishal.moneytrackerapp.ui.auth.AuthActivity
import com.bhavsar.vishal.moneytrackerapp.ui.home.HomeActivity
import com.bhavsar.vishal.moneytrackerapp.ui.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer {
            val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        })
    }
}