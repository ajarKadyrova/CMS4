package com.example.customermanagementsystem.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customermanagementsystem.models.*
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class ViewModel(private val repository : Repository) : ViewModel(){

    val myResponse : MutableLiveData<Response<RegisterUserResult>> = MutableLiveData()
    val myAuthResponse: MutableLiveData<Response<AuthUserResult>> = MutableLiveData()
    val newClientResponse: MutableLiveData<Response<ResponseBody>> = MutableLiveData()
    val changeClientStatusResponse: MutableLiveData<Response<ResponseBody>> = MutableLiveData()
    val allGroups: MutableLiveData<Response<List<GroupDTO>>> = MutableLiveData()
    val allStudents: MutableLiveData<Response<List<StudentsDTO>>> = MutableLiveData()
    val myGroup: MutableLiveData<Response<GroupDTO>> = MutableLiveData()
    val allTeachers: MutableLiveData<Response<List<TeacherDTO>>> = MutableLiveData()
    val allRooms: MutableLiveData<Response<List<RoomDTO>>> = MutableLiveData()
    val allCourses: MutableLiveData<Response<List<CourseDTO>>> = MutableLiveData()
    val allClients: MutableLiveData<Response<List<ClientDTO>>> = MutableLiveData()
    val allBoards: MutableLiveData<Response<List<ClientDTO>>> = MutableLiveData()
    val newBoard: MutableLiveData<Response<ResponseBody>> = MutableLiveData()
    val myEmail: MutableLiveData<Response<ResponseBody>> = MutableLiveData()
    val newGroup: MutableLiveData<Response<ResponseBody>> = MutableLiveData()
    val studentWoutGroup: MutableLiveData<Response<List<StudentsDTO>>> = MutableLiveData()
    val addStudentGroup: MutableLiveData<Response<ResponseBody>> = MutableLiveData()

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

    fun createClient(branchId: Int, newClient: PostClient){
        viewModelScope.launch {
            val response = repository.createClient(branchId, newClient)
            newClientResponse.value = response
        }
    }
    fun changeClientStatus(clientId : Long, toBoardId: Int){
        viewModelScope.launch {
            val response = repository.changeClientStatus(clientId, toBoardId)
            changeClientStatusResponse.value = response
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

    fun getAllCourses(){
        viewModelScope.launch {
            val response = repository.getAllCourses()
            allCourses.value = response
        }
    }

    fun getAllClients(branchId: Int, criteria:Any = Object()){
        viewModelScope.launch {
            val response = repository.getAllClients(branchId, criteria)
            allClients.value = response
        }
    }

    fun getAllBoards(branchID: Int, body: Filter?){
        viewModelScope.launch {
            val response = body?.let { repository.getAllBoards(branchID, it) }
            allBoards.value = response
        }
    }

    fun createBoard(newBoardName: PostNewBoard){
        viewModelScope.launch {
            val response = repository.createBoard(newBoardName)
            newBoard.value = response
        }
    }

    fun getRecoveryPassword(email: String){
        viewModelScope.launch {
            val response = repository.getRecoveryCode(email)
            newBoard.value = response
        }
    }

    fun createGroup(branchId: Int, group: PostGroup){
        viewModelScope.launch {
            val response = repository.createGroup(branchId, group)
            newGroup.value = response
        }
    }

    fun getStudentsWoutGroups(branchId: Int){
        viewModelScope.launch {
            val response = repository.getStudentsWoutGroups(branchId)
            studentWoutGroup.value = response
        }
    }

    fun addStudentToGroup(id: Long, branchId: Int, student: StudentsDTO){
        viewModelScope.launch {
            val response = repository.addStudentToGroup(id, branchId, student)
            addStudentGroup.value = response
        }
    }
}