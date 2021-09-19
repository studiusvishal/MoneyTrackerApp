package com.bhavsar.vishal.moneytrackerapp.ui.base

import androidx.lifecycle.ViewModel
import com.bhavsar.vishal.moneytrackerapp.data.network.UserApi
import com.bhavsar.vishal.moneytrackerapp.data.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {
    suspend fun logout(api: UserApi) = withContext(Dispatchers.IO) { repository.logout(api) }
}