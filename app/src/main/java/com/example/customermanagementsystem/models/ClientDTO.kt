package com.example.customermanagementsystem.models

data class ClientDTO(
        val name: String,
        val email: String,
        val phoneNumber: String,
        val wantsCourse: CourseDTO,
        val boards: BoardsDTO,
        val hasLaptop: Boolean,
        val utmSource: String,
        val comments: String,
        val branch: BranchDTO,
        val clientIP: String,
        val id: Int,
        val ymClientID: String
)