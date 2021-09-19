package com.bhavsar.vishal.moneytrackerapp.ui.expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavsar.vishal.moneytrackerapp.data.network.Resource
import com.bhavsar.vishal.moneytrackerapp.data.payload.responses.expense.AddExpenseCategoryResponse
import com.bhavsar.vishal.moneytrackerapp.data.repository.BaseRepository
import com.bhavsar.vishal.moneytrackerapp.data.repository.ExpenseRepository
import com.bhavsar.vishal.moneytrackerapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ExpenseCategoryViewModel(
    private val expenseRepository: ExpenseRepository
) : BaseViewModel(expenseRepository) {
    private val _addCategoryResponse: MutableLiveData<Resource<AddExpenseCategoryResponse>> =
        MutableLiveData()
    val addCategoryResponse: LiveData<Resource<AddExpenseCategoryResponse>>
        get() = _addCategoryResponse

    fun saveCategory(categoryName: String) = viewModelScope.launch {
        _addCategoryResponse.value = expenseRepository.addExpenseCategory(categoryName)
    }
}