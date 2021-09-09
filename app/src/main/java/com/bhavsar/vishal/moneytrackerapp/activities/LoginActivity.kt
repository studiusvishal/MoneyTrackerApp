package com.bhavsar.vishal.moneytrackerapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bhavsar.vishal.moneytrackerapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}