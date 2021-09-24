package com.bhavsar.vishal.moneytrackerapp.ui.auth

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bhavsar.vishal.moneytrackerapp.R
import com.bhavsar.vishal.moneytrackerapp.data.network.AuthApi
import com.bhavsar.vishal.moneytrackerapp.data.network.Resource
import com.bhavsar.vishal.moneytrackerapp.data.repository.AuthRepository
import com.bhavsar.vishal.moneytrackerapp.databinding.FragmentRegisterBinding
import com.bhavsar.vishal.moneytrackerapp.ui.base.BaseFragment
import com.bhavsar.vishal.moneytrackerapp.ui.enable
import com.bhavsar.vishal.moneytrackerapp.ui.handleApiError
import com.bhavsar.vishal.moneytrackerapp.ui.snackbar
import com.bhavsar.vishal.moneytrackerapp.ui.visible
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<AuthViewModel, FragmentRegisterBinding, AuthRepository>() {
    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentRegisterBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.signUpProgressBar.visible(false)
        binding.buttonSignUp.enable(false)

        viewModel.signUpResponse.observe(viewLifecycleOwner, {
            binding.signUpProgressBar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        requireView().snackbar(it.value.message)
                    }
                }
                is Resource.Failure -> handleApiError(it) { // TODO: update to handle errors for sign up
                    // login()
                }
            }
        })

        binding.editTextConfirmPassword.addTextChangedListener {
            val fullName = binding.editTextSignUpFullName.text.toString().trim()
            val email = binding.editTextSignUpEmail.text.toString().trim()
            val username = binding.editTextSignUpUsername.text.toString().trim()
            val password = binding.editTextSignUpPassword.text.toString().trim()
            val confirmPassword = it.toString().trim()
            val valid = username.isNotEmpty()
                    && confirmPassword.isNotEmpty()
                    && fullName.isNotEmpty()
                    && email.isNotEmpty()
                    && Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    && password.isNotEmpty()
                    && confirmPassword == password
            binding.buttonSignUp.enable(valid)
        }

        binding.buttonSignUp.setOnClickListener {
            signUp()
        }

        binding.textViewLogin.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_RegisterFragment_to_LoginFragment)
        }
    }

    private fun signUp() {
        val fullName = binding.editTextSignUpFullName.text.toString().trim()
        val email = binding.editTextSignUpEmail.text.toString().trim()
        val username = binding.editTextSignUpUsername.text.toString().trim()
        val password = binding.editTextSignUpPassword.text.toString().trim()
        val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

        if (confirmPassword != password) {
            binding.editTextSignUpPassword.error = "Passwords don\'t match."
            binding.editTextConfirmPassword.error = "Passwords don\'t match."
            return
        }
        viewModel.signUp(fullName, username, email, password)
    }
}