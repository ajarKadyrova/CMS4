package com.example.customermanagementsystem.models

data class ClientCriteria(
    val courseList: List<CourseDTO>,
    val fromDate: String,
    val searchField: String,
    val toDate: String
)