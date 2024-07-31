package com.raychow.expensecalculator.ui.expensecalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ExpenseCalculatorView(modifier: Modifier, expenseCalculatorViewModel: ExpenseCalculatorViewModel = viewModel()) {
    val gameUiState by expenseCalculatorViewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}

@Composable
fun GroceryTextField(
    viewModel: ExpenseCalculatorViewModel,
) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        modifier = Modifier.focusRequester(focusRequester),
        value = viewModel.grocery,
        onValueChange = {
            if(it.isDigitsOnly())
                viewModel.grocery = it
            else
                showSnackBar(scope, snackBarHostState, focusRequester)
        },
        label = { Text(text = "Grocery") },
        placeholder ={ Text(text = "A") },
        keyboardOptions = KeyboardOptions(keyboardType= KeyboardType.Number, imeAction = ImeAction.Next)
    )
}

private fun showSnackBar(
    scope: CoroutineScope,
    snackBarHostState: SnackbarHostState,
    focusRequester: FocusRequester
) {
    scope.launch {
        val result = snackBarHostState
            .showSnackbar(
                message = "SnackBar",
                actionLabel = "Action",
                // Defaults to SnackBarDuration.Short
                duration = SnackbarDuration.Indefinite
            )
        when (result) {
            SnackbarResult.Dismissed -> TODO()
            SnackbarResult.ActionPerformed -> {
                focusRequester.requestFocus()
            }
        }
    }
}