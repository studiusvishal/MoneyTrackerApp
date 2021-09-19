package com.bhavsar.vishal.moneytrackerapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.bhavsar.vishal.moneytrackerapp.data.network.AuthApi
import com.bhavsar.vishal.moneytrackerapp.data.network.Resource
import com.bhavsar.vishal.moneytrackerapp.data.repository.AuthRepository
import com.bhavsar.vishal.moneytrackerapp.databinding.FragmentLoginBinding
import com.bhavsar.vishal.moneytrackerapp.ui.base.BaseFragment
import com.bhavsar.vishal.moneytrackerapp.ui.enable
import com.bhavsar.vishal.moneytrackerapp.ui.handleApiError
import com.bhavsar.vishal.moneytrackerapp.ui.home.HomeActivity
import com.bhavsar.vishal.moneytrackerapp.ui.startNewActivity
import com.bhavsar.vishal.moneytrackerapp.ui.visible
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.loginProgressBar.visible(false)
        binding.buttonLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, {
            binding.loginProgressBar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.token!!)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it)
            }
        })

        binding.editTextLoginPassword.addTextChangedListener {
            val username = binding.editTextLoginUsername.text.toString().trim()
            binding.buttonLogin.enable(username.isNotEmpty() && it.toString().trim().isNotEmpty())
        }

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
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)
}