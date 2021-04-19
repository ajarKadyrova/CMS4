package com.example.customermanagementsystem.models

class ClientDTO (
        val id: Long,
        val boardName: String,
        val queueNumber: Int,
        val clients: List<Client>
)