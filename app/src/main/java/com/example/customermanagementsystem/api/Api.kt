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
}