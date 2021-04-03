package com.example.customermanagementsystem.api

import com.example.customermanagementsystem.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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
        @Query("branchID") branchId: Int
    ):Response<ClientDTO>

    @GET("api/v1/groups")
    suspend fun getAllGroups(
        @Query("branchID") branchId: Int
    ):Response<List<GroupDTO>>
}