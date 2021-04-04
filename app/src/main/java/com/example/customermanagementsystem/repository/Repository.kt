package com.example.customermanagementsystem.repository

import com.example.customermanagementsystem.api.RetrofitInstance
import com.example.customermanagementsystem.models.*
import retrofit2.Response

class Repository {

    suspend fun registerUser(user:RegistrationModel): Response<RegisterUserResult> {
        return RetrofitInstance.api.registerUser(user)
    }

    suspend fun authUser(authUser: AuthUser):Response<AuthUserResult>{
        return RetrofitInstance.api.authUser(authUser)
    }

    suspend fun createClient(branchId: Int):Response<ClientDTO>{
        return RetrofitInstance.api.createClient(branchId)
    }

    suspend fun getAllGroups(branchId: Int):Response<List<GroupDTO>>{
        return RetrofitInstance.api.getAllGroups(branchId)
    }

    suspend fun getAllStudents(branchId: Int):Response<List<StudentsDTO>>{
        return RetrofitInstance.api.getAllStudents(branchId)
    }
}