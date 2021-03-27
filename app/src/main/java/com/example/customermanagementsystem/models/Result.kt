package com.example.customermanagementsystem.models

data class Result(
    val active: Boolean,
    val branch: Branch,
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val roles: List<Role>
)