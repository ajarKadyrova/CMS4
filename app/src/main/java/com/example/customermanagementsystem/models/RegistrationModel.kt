package com.example.customermanagementsystem.models

data class RegistrationModel (
    val confirmPassword:String,
    val email:String,
    val firstName:String,
    val lastName:String,
    val password: String
)