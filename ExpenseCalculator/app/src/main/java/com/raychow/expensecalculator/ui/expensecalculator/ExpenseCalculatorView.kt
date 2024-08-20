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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raychow.expensecalculator.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ExpenseCalculatorView(modifier: Modifier, viewModel: ExpenseCalculatorViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    val focusRequesterGrocery = remember { FocusRequester() }
    val focusRequesterMortgageOrRent = remember { FocusRequester() }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NumberTextField(
            modifier = Modifier.focusRequester(focusRequesterGrocery),
            value = formatNumber(uiState.grocery),
            onValueChange = {
                if(it.isDigitsOnly())
                    viewModel.updateGrocery(it.toDouble())
                else
                    showSnackBar(
                        scope = scope,
                        snackBarHostState = snackBarHostState,
                        focusRequester = focusRequesterGrocery,
                    )
            },
            placeholderText = ""
        )
        NumberTextField(
            modifier = Modifier.focusRequester(focusRequesterMortgageOrRent),
            value = formatNumber(uiState.mortgageOrRent),
            onValueChange = {
                if(it.isDigitsOnly())
                    viewModel.mortgageOrRent = it.toDouble()
                else
                    showSnackBar(
                        scope = scope,
                        snackBarHostState = snackBarHostState,
                        focusRequester = focusRequesterMortgageOrRent,
                    )
            },
            placeholderText = ""
        )
    }
}

fun formatNumber(double: Double): String {
    if (double == 0.0){
        return ""
    }else{
        return double.toString()
    }
}

@Composable
fun NumberTextField(
    modifier: Modifier, value: String, onValueChange:(String)->Unit, placeholderText:String
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(R.string.grocery)) },
        placeholder ={ Text(text = placeholderText) },
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