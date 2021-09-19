package com.bhavsar.vishal.moneytrackerapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bhavsar.vishal.moneytrackerapp.data.network.Resource
import com.bhavsar.vishal.moneytrackerapp.data.network.UserApi
<<<<<<< HEAD
import com.bhavsar.vishal.moneytrackerapp.data.payload.responses.LoginResponse
=======
import com.bhavsar.vishal.moneytrackerapp.data.payload.responses.User
>>>>>>> 80ca19bea32b76c8e4270b1d0133d2d2286f7109
import com.bhavsar.vishal.moneytrackerapp.data.repository.UserRepository
import com.bhavsar.vishal.moneytrackerapp.databinding.FragmentHomeBinding
import com.bhavsar.vishal.moneytrackerapp.ui.base.BaseFragment
import com.bhavsar.vishal.moneytrackerapp.ui.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {
    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visible(false)
        viewModel.getUser()
        viewModel.user.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    binding.progressbar.visible(false)
<<<<<<< HEAD
                    updateUI(it.value)
=======
                    updateUI(it.value.user)
>>>>>>> 80ca19bea32b76c8e4270b1d0133d2d2286f7109
                }
                is Resource.Loading -> {
                    binding.progressbar.visible(true)
                }
            }
        })
    }

<<<<<<< HEAD
    private fun updateUI(user: LoginResponse) {
=======
    private fun updateUI(user: User) {
>>>>>>> 80ca19bea32b76c8e4270b1d0133d2d2286f7109
        with(binding) {
            textViewId.text = user.id.toString()
            textViewName.text = user.name
            textViewEmail.text = user.email
        }
    }
}