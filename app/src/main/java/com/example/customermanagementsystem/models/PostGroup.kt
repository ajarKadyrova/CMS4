package com.example.customermanagementsystem.models

data class PostGroup(
    val amount: Int,
    val course: BoardID,
    val endDate: String,
    val groupName: String,
    val startDate: String,
    val teacher: BoardID,
    val timeTable: TimeTable
)