package com.raychow.expensecalculator.viewmodels

class TextFieldViewModel: ViewModel() {
    var text by mutableStateOf("")
        private set
    fun setValue(value: String){
        text = value
    }
}