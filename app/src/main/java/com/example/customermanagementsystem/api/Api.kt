package com.example.customermanagementsystem.api

import com.example.customermanagementsystem.models.*
import retrofit2.Response
import retrofit2.http.*

interface Api {
    @POST("api/v1/auth/sign-up")
    suspend fun registerUser(
        @Body user : RegistrationModel
    ) : Response<RegisterUserResult>

    @POST("api/v1/auth/sign-in")
    suspend fun authUser(
            @Body authUser : AuthUser
    ) : Response<AuthUserResult>

    @POST("api/v1/clients")
    suspend fun createClient(
        @Query("branchID") branchId: Int,
        @Body newClient: ClientDTO
    ):Response<String>

    @POST("/api/v1/client-boards/getBoards")
    suspend fun getAllClients(
            @Query("branchId") branchId: Int,
            @Body criteria:Any = Object()
    ):Response<List<ClientDTO>>

    @GET("api/v1/groups")
    suspend fun getAllGroups(
        @Query("branchID") branchId: Int
    ):Response<List<GroupDTO>>

    @GET("api/v1/students")
    suspend fun getAllStudents(
            @Query("branchID") branchId: Int
    ):Response<List<StudentsDTO>>

    @GET("api/v1/groups/{id}")
    suspend fun getGroup(
            @Path("id") id: Long,
            @Query("branchID") branchId: Int
    ): Response<GroupDTO>

    @GET("api/v1/teachers")
    suspend fun getAllTeachers(
            @Query("branchID") branchId: Int
    ): Response<List<TeacherDTO>>

    @GET("api/v1/rooms")
    suspend fun getAllRooms(
            @Query("branchID") branchId: Int
    ): Response<List<RoomDTO>>

    @GET("api/v1/courses")
    suspend fun getAllCourses(
            @Query("branchID") branchId: Int
    ): Response<List<CourseDTO>>
}