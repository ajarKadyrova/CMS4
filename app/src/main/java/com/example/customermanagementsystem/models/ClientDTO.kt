package com.example.customermanagementsystem.models

import java.io.Serializable


class ClientDTO (
        val id: Long,
        val boardName: String,
        val queueNumber: Int,
        val clients: List<Client>

) : Serializable