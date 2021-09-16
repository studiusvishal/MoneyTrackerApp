package com.bhavsar.vishal.moneytrackerapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bhavsar.vishal.moneytrackerapp.data.network.AuthApi
import com.bhavsar.vishal.moneytrackerapp.data.network.Resource
import com.bhavsar.vishal.moneytrackerapp.data.repository.AuthRepository
import com.bhavsar.vishal.moneytrackerapp.databinding.FragmentLoginBinding
import com.bhavsar.vishal.moneytrackerapp.ui.base.BaseFragment
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        userPreferences.saveAuthToken(it.value.token)
                    }
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
        })
        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextLoginUsername.text.toString().trim()
            val password = binding.editTextLoginPassword.text.toString().trim()
            // TODO: Add input validations
            viewModel.login(username, password)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))
}