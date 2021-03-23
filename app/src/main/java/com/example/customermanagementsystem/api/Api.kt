package com.example.customermanagementsystem.api

import com.example.customermanagementsystem.models.RegistrationModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @POST("api/v1/auth/sign-up")
    suspend fun registerUser(
        @Body user : RegistrationModel
    ) : Response<RegistrationModel>
}