package com.example.customermanagementsystem.models

import java.io.Serializable

class StudentsDTO(
        val amount: Long,
        val firstName: String,
        val lastName: String,
        val patronymic: String,
        val archived: Boolean,
        val branch: BranchDTO,
        val completedCourses: List<Any>,
        val email: String,
        val group: GroupDTO,
        val id: Long,
        val paymentJournals: List<PaymentJournalDTO>,
        val payments: List<PaymentDTO>,
        val phoneNumber: String,
        val wantsCourse: CourseDTO
):Serializable