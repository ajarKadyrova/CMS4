package com.example.customermanagementsystem.models

data class Filter(
        val courseList: BoardID?,
        val fromDate: String?,
        val searchField: String?,
        val toDate: String?
)