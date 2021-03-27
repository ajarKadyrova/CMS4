package com.example.customermanagementsystem.models

data class Role(
    val archived: Boolean,
    val createdAt: String,
    val createdBy: String,
    val id: Int,
    val permissions: List<String>,
    val roleName: String
)