package com.example.customermanagementsystem.models

data class BoardsDTO(
    val boardName: String,
    val clients: List<Any>,
    val id: Int,
    val queueNumber: Int
)