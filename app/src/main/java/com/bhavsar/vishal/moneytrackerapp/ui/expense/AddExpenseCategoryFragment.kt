package com.bhavsar.vishal.moneytrackerapp.ui.expense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.bhavsar.vishal.moneytrackerapp.data.network.ExpenseApi
import com.bhavsar.vishal.moneytrackerapp.data.network.Resource
import com.bhavsar.vishal.moneytrackerapp.data.repository.ExpenseRepository
import com.bhavsar.vishal.moneytrackerapp.databinding.FragmentAddExpenseCategoryBinding
import com.bhavsar.vishal.moneytrackerapp.ui.base.BaseFragment
import com.bhavsar.vishal.moneytrackerapp.ui.enable
import com.bhavsar.vishal.moneytrackerapp.ui.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class AddExpenseCategoryFragment :
    BaseFragment<ExpenseCategoryViewModel, FragmentAddExpenseCategoryBinding, ExpenseRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addExpenseCategoryProgressBar.visible(false)
        binding.buttonSaveCategory.enable(false)

        viewModel.addCategoryResponse.observe(viewLifecycleOwner, {
            binding.addExpenseCategoryProgressBar.visible(true)
            when (it) {
                is Resource.Success -> {
                    // show toast
                }
                is Resource.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Failed to save new category!!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        binding.editTextExpenseCategory.addTextChangedListener {
            binding.buttonSaveCategory.enable(it.toString().trim().isNotEmpty())
        }

        binding.buttonSaveCategory.setOnClickListener {
            val category = binding.editTextExpenseCategory.text.toString().trim()
            // TODO: Add input validations
            binding.addExpenseCategoryProgressBar.visible(true)
            viewModel.saveCategory(category)
        }
    }

    override fun getViewModel() = ExpenseCategoryViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAddExpenseCategoryBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ExpenseRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        return ExpenseRepository(remoteDataSource.buildApi(ExpenseApi::class.java, token))
    }
}