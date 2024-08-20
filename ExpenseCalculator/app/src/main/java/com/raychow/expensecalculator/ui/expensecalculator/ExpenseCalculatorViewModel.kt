package com.raychow.expensecalculator.ui.expensecalculator

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExpenseCalculatorViewModel(application: Application): AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(ExpenseCalculatorUIState())
    val uiState: StateFlow<ExpenseCalculatorUIState> = _uiState.asStateFlow()

    var grocery by mutableStateOf(0.0)

    var mortgageOrRent by mutableStateOf(0.0)

    var utilities by mutableStateOf(0.0)

    var others by mutableStateOf(0.0)
//    , mortgageOrRent:Double, utilities:Double, others:Double
    fun updateGrocery(grocery: Double){
        this.grocery = grocery
        _uiState.value = ExpenseCalculatorUIState(grocery = grocery)
    }
}