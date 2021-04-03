package com.example.customermanagementsystem.models

class StudentsDTO(
    val amount: Number,
    val archived: Boolean,
    val branchDTO: BranchDTO,
    val email: String,
    val id: Int,
    val paymentJournalDTO: PaymentJournalDTO,
    val paymentDTO: List<PaymentDTO>,
    val phoneNumber: String,
    val studentName : String
)