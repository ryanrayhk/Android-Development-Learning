package com.raychow.expensecalculator.ui.expensecalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class ExpenseCalculatorUIState(
    // Household
    val grocery: Double = 0.0,
    val mortgageOrRent: Double = 0.0,
    val utilities: Double = 0.0,
    val others: Double = 0.0,

    // Leisure
    val DineOutsAndShopping:Double = 0.0,
    //...
)
