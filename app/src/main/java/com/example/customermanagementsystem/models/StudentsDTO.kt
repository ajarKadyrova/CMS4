package com.example.customermanagementsystem.models

class StudentsDTO(
        val amount: Long,
        val archived: Boolean,
        val branch: BranchDTO,
        val completedCourses: List<Any>,
        val email: String,
        val group: GroupDTO,
        val id: Long,
        val paymentJournals: List<PaymentJournalDTO>,
        val payments: List<PaymentDTO>,
        val phoneNumber: String,
        val studentName: String
)