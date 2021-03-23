package com.example.customermanagementsystem

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customermanagementsystem.models.RegistrationModel
import com.example.customermanagementsystem.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel(private val repository : Repository) : ViewModel(){

    val myResponse : MutableLiveData<Response<RegistrationModel>> = MutableLiveData()

    fun registerUser(user: RegistrationModel){
        viewModelScope.launch {
            val response = repository.registerUser(user)
            myResponse.value = response
        }
    }
}