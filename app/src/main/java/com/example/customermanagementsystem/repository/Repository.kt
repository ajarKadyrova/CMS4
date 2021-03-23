package com.example.customermanagementsystem.repository

import com.example.customermanagementsystem.api.RetrofitInstance
import com.example.customermanagementsystem.models.RegistrationModel
import retrofit2.Response

class Repository {

    suspend fun registerUser(user:RegistrationModel): Response<RegistrationModel> {
        return RetrofitInstance.api.registerUser(user)
    }

}