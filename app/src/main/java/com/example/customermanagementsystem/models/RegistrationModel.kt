package com.example.customermanagementsystem.models

import android.text.Editable

data class RegistrationModel(
        val confirmPassword: String,
        val email: String,
        val firstName: String,
        val lastName: String,
        val password: String
)