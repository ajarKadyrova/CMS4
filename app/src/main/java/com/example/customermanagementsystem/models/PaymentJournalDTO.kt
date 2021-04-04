package com.example.customermanagementsystem.models

class PaymentJournalDTO (
    val amount: Long,
    val dateOfPayment: String,
    val id: Long,
    val paymentType: PaymentTypeDTO
)