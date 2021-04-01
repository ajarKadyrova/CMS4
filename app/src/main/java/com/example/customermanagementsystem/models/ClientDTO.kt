package com.example.customermanagementsystem.models

data class ClientDTO(
    val CourseDTO: CourseDTO,
    val boardsDTO: BoardsDTO,
    val branchDTO: BranchDTO,
    val clientIP: String,
    val comments: String,
    val email: String,
    val hasLaptop: Boolean,
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val utmSource: String,
    val ymClientID: String
)