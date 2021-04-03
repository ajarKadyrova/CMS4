package com.example.customermanagementsystem.models

class PaymentJournalDTO (
    val amount: Number,
    val dateOfPayment: String,
    val id: Int,
    val paymentTypeDTO: PaymentTypeDTO
)