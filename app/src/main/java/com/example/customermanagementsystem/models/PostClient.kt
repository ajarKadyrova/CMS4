package com.example.customermanagementsystem.models

data class PostClient(
    val boardsID: BoardID,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val email: String,
    val phoneNumber: String,
    val hasLaptop: Boolean,
    val wantCourseID: BoardID,
    val utmSource: String,
    val comments: String

)