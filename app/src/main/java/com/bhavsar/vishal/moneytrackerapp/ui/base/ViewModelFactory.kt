package com.bhavsar.vishal.moneytrackerapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bhavsar.vishal.moneytrackerapp.data.repository.AuthRepository
import com.bhavsar.vishal.moneytrackerapp.data.repository.BaseRepository
import com.bhavsar.vishal.moneytrackerapp.ui.auth.AuthViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            // TODO Add code to get new repository instance
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }
}