package com.example.customermanagementsystem.models

data class TimeTableDTO(
    val daysOfWeeks: List<String>,
    val endTime: String,
    val id: Int,
    val startTime: String,
    val room: RoomDTO
)