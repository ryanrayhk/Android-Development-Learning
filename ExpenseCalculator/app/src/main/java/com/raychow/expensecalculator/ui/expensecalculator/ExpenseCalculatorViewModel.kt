package com.raychow.expensecalculator.ui.expensecalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExpenseCalculatorViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ExpenseCalculatorUIState())
    val uiState: StateFlow<ExpenseCalculatorUIState> = _uiState.asStateFlow()

    var grocery by mutableStateOf("")

    var mortgageOrRent by mutableStateOf("")

    var utilities by mutableStateOf("")

    var others by mutableStateOf("")
}