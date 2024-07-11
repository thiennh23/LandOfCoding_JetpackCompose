package com.example.landofcoding_jetpackcompose

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel(){

    //Create public a state
    val state = mutableStateOf(MyScreenState())

    fun updateText(newText: String) {
        state.value = state.value.copy(text = newText)
    }

    fun updateNameList(newName: String) {
        val currentList = state.value.namesList
        currentList.add(newName)
        state.value = state.value.copy(namesList = currentList)
    }
}