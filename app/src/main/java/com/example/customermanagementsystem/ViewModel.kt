package com.example.customermanagementsystem

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customermanagementsystem.models.*
import com.example.customermanagementsystem.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModel(private val repository : Repository) : ViewModel(){

    val myResponse : MutableLiveData<Response<RegisterUserResult>> = MutableLiveData()
    val myAuthResponse: MutableLiveData<Response<AuthUserResult>> = MutableLiveData()
    val newClientResponse: MutableLiveData<Response<ClientDTO>> = MutableLiveData()

    fun registerUser(user: RegistrationModel){
        viewModelScope.launch {
            val response = repository.registerUser(user)
            myResponse.value = response
        }
    }

    fun authUser(authUser: AuthUser){
        viewModelScope.launch {
            val response = repository.authUser(authUser)
            myAuthResponse.value = response
        }
    }

    fun createClient(branchId: Int){
        viewModelScope.launch {
            val response = repository.createClient(branchId)
            newClientResponse.value = response
        }
    }
}