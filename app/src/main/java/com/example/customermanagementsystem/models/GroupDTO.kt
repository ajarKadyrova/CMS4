package com.example.customermanagementsystem.models

class GroupDTO (
    val amount: Long,
    val branch:BranchDTO,
    val course: CourseDTO,
    val startDate: String,
    val endDate: String,
    val groupName: String,
    val id: Int,
    val numberOfStudents: Int,
    val students: List<StudentsDTO>,
    val teacher: TeacherDTO,
    val timeTable: TimeTableDTO
)