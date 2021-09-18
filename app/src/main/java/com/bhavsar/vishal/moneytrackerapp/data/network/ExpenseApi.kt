package com.bhavsar.vishal.moneytrackerapp.data.network

import com.bhavsar.vishal.moneytrackerapp.data.payload.requests.expense.AddExpenseCategoryRequest
import com.bhavsar.vishal.moneytrackerapp.data.payload.responses.expense.AddExpenseCategoryResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ExpenseApi {
    @POST("/api/expense/addExpenseCategory")
    suspend fun addExpenseCategory(
        @Body addExpenseCategoryRequest: AddExpenseCategoryRequest
    ): AddExpenseCategoryResponse
}