package com.bhavsar.vishal.moneytrackerapp.data.repository

import com.bhavsar.vishal.moneytrackerapp.data.network.ExpenseApi
import com.bhavsar.vishal.moneytrackerapp.data.payload.requests.expense.AddExpenseCategoryRequest

class ExpenseRepository(
    private val api: ExpenseApi
) : BaseRepository() {
    suspend fun addExpenseCategory(categoryName: String) = safeApiCall {
        api.addExpenseCategory(AddExpenseCategoryRequest(categoryName))
    }
}