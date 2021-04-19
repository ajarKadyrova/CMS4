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
    val newClientResponse: MutableLiveData<Response<String>> = MutableLiveData()
    val allGroups: MutableLiveData<Response<List<GroupDTO>>> = MutableLiveData()
    val allStudents: MutableLiveData<Response<List<StudentsDTO>>> = MutableLiveData()
    val myGroup: MutableLiveData<Response<GroupDTO>> = MutableLiveData()
    val allTeachers: MutableLiveData<Response<List<TeacherDTO>>> = MutableLiveData()
    val allRooms: MutableLiveData<Response<List<RoomDTO>>> = MutableLiveData()
    val allCourses: MutableLiveData<Response<List<CourseDTO>>> = MutableLiveData()
    val allClients: MutableLiveData<Response<List<ClientDTO>>> = MutableLiveData()

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

    fun createClient(branchId: Int, newClient: ClientDTO){
        viewModelScope.launch {
            val response = repository.createClient(branchId, newClient)
            newClientResponse.value = response
        }
    }

    fun getAllGroups(branchId: Int){
        viewModelScope.launch {
            val response = repository.getAllGroups(branchId)
            allGroups.value = response
        }
    }

    fun getAllStudents(branchId: Int){
        viewModelScope.launch {
            val response = repository.getAllStudents(branchId)
            allStudents.value = response
        }
    }

    fun getGroup(branchId: Int, id: Long){
        viewModelScope.launch {
            val response = repository.getGroup(branchId, id)
            myGroup.value = response
        }
    }

    fun getAllTeachers(branchId: Int){
        viewModelScope.launch {
            val response = repository.getAllTeachers(branchId)
            allTeachers.value = response
        }
    }

    fun getAllRooms(branchId: Int){
        viewModelScope.launch {
            val response = repository.getAllRooms(branchId)
            allRooms.value = response
        }
    }

    fun getAllCourses(branchId: Int){
        viewModelScope.launch {
            val response = repository.getAllCourses(branchId)
            allCourses.value = response
        }
    }

    fun getAllClients(branchId: Int, criteria:Any = Object()){
        viewModelScope.launch {
            val response = repository.getAllClients(branchId, criteria)
            allClients.value = response
        }
    }
}