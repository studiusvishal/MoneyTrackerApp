package com.bhavsar.vishal.moneytrackerapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bhavsar.vishal.moneytrackerapp.data.network.Resource
import com.bhavsar.vishal.moneytrackerapp.data.payload.responses.LoginResponse
import com.bhavsar.vishal.moneytrackerapp.data.payload.responses.SignUpResponse
import com.bhavsar.vishal.moneytrackerapp.data.repository.AuthRepository
import com.bhavsar.vishal.moneytrackerapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    private val _signUpResponse: MutableLiveData<Resource<SignUpResponse>> = MutableLiveData()
    val signUpResponse: LiveData<Resource<SignUpResponse>>
        get() = _signUpResponse

    fun login(username: String, password: String) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(username, password)
    }

    suspend fun saveAuthToken(token: String) {
        repository.saveAuthToken(token)
    }

    fun signUp(fullName: String, username: String, email: String, password: String) =
        viewModelScope.launch {
            _signUpResponse.value = Resource.Loading
            _signUpResponse.value = repository.signUp(fullName, username, email, password)
        }
}