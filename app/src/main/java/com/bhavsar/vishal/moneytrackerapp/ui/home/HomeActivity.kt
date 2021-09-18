package com.bhavsar.vishal.moneytrackerapp.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bhavsar.vishal.moneytrackerapp.R

class HomeActivity : AppCompatActivity() {
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//
//        val rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim)
//        val rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim)
//        val fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim)
//        val toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim)
    }
//
//    fun performLogout() = lifecycleScope.launch {
//        viewModel.logout()
//        userPreferences.clear()
//        startNewActivity(AuthActivity::class.java)
//    }
}