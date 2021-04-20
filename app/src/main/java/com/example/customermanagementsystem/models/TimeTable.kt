package com.example.customermanagementsystem.models

data class TimeTable(
    val daysOfWeeks: List<String>,
    val endTime: String,
    val room: BoardID,
    val startTime: String
)