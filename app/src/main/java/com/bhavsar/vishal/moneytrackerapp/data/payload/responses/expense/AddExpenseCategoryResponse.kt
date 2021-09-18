package com.bhavsar.vishal.moneytrackerapp.data.payload.responses.expense

data class AddExpenseCategoryResponse(
    val msg: String,
    val records: List<Record>,
    val size: Int
)