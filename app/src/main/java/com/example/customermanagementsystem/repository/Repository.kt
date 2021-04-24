package com.example.customermanagementsystem.repository

import com.example.customermanagementsystem.api.RetrofitInstance
import com.example.customermanagementsystem.models.*
import okhttp3.ResponseBody
import retrofit2.Response

class Repository {

    suspend fun registerUser(user:RegistrationModel): Response<RegisterUserResult> {
        return RetrofitInstance.api.registerUser(user)
    }

    suspend fun authUser(authUser: AuthUser):Response<AuthUserResult>{
        return RetrofitInstance.api.authUser(authUser)
    }

    suspend fun createClient(branchId: Int, newClient: PostClient):Response<ResponseBody>{
        return RetrofitInstance.api.createClient(branchId, newClient)
    }
    suspend fun changeClientStatus(clientId: Long, toBoardId: Int) : Response<ResponseBody>{
        return RetrofitInstance.api.changeClientStatus(clientId, toBoardId)
    }

    suspend fun getAllGroups(branchId: Int):Response<List<GroupDTO>>{
        return RetrofitInstance.api.getAllGroups(branchId)
    }

    suspend fun getAllStudents(branchId: Int):Response<List<StudentsDTO>>{
        return RetrofitInstance.api.getAllStudents(branchId)
    }

    suspend fun getGroup(branchId: Int, id: Int):Response<GroupDTO>{
        return RetrofitInstance.api.getGroup(id, branchId)
    }

    suspend fun getAllTeachers(branchId: Int):Response<List<TeacherDTO>>{
        return RetrofitInstance.api.getAllTeachers(branchId)
    }

    suspend fun getAllRooms(branchId: Int):Response<List<RoomDTO>>{
        return RetrofitInstance.api.getAllRooms(branchId)
    }

    suspend fun getAllCourses():Response<List<CourseDTO>>{
        return RetrofitInstance.api.getAllCourses()
    }

    suspend fun getAllClients(branchId: Int, criteria:Any = Object()):Response<List<ClientDTO>>{
        return RetrofitInstance.api.getAllClients(branchId, criteria)
    }

    suspend fun getAllBoards(branchId: Int, body:Filter):Response<List<ClientDTO>>{
        return RetrofitInstance.api.getAllBoards(branchId, body)
    }

    suspend fun createBoard(newBoardName: PostNewBoard):Response<ResponseBody>{
        return RetrofitInstance.api.createBoard(newBoardName)
    }

    suspend fun getRecoveryCode(email:String):Response<ResponseBody>{
        return RetrofitInstance.api.getRecoveryCode(email)
    }

    suspend fun createGroup(branchId: Int, newGroup: PostGroup):Response<ResponseBody>{
        return RetrofitInstance.api.createGroup(branchId, newGroup)
    }

    suspend fun getStudentsWoutGroups(branchId: Int):Response<List<StudentsDTO>>{
        return RetrofitInstance.api.getStudentsWoutGroups(branchId)
    }

    suspend fun addStudentToGroup(studentId: Long, branchId: Int, groupId: Int):Response<ResponseBody>{
        return RetrofitInstance.api.addStudentToGroup(studentId, branchId,  groupId)
    }
}