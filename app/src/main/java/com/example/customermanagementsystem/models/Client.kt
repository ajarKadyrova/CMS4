package com.example.customermanagementsystem.models

import java.io.Serializable

data class Client(
        val boards: BoardsDTO,
        val firstName: String,
        val lastName: String,
        val patronymic: String,
        val email: String,
        val phoneNumber: String,
        val wantsCourse: CourseDTO,
        val hasLaptop: Boolean,
        val utmSource: String,
        val comments: String,
        val branch: BranchDTO,
        val clientIP: String,
        val id: Long,
        val ymClientID: String,
        val registrationDate: String
):Serializable