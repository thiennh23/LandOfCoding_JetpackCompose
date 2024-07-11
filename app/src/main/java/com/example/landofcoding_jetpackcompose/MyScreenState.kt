package com.example.landofcoding_jetpackcompose

data class MyScreenState(
//    Pass the state variables to this
    val text: String = "",
    val namesList: MutableList<String> = mutableListOf()
)
