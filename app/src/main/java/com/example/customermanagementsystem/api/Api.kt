package com.example.customermanagementsystem.api

import com.example.customermanagementsystem.models.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import java.util.*

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
        @Body newClient: PostClient
    ):Response<ResponseBody>

    @POST("/api/v1/clients/get")
    suspend fun getAllClients(
            @Query("branchID") branchId: Int,
            @Body newClient: Any = Object()
    ):Response<List<ClientDTO>>

    @POST("/api/v1/client-boards/getBoards")
    suspend fun getAllBoards(
            @Query("branchID") branchId: Int,
            @Body body:Filter
    ):Response<List<ClientDTO>>

    @Headers("Content-Type: application/json")
    @PUT("/api/v1/client-boards")
    suspend fun changeClientStatus(
        @Query("clientID") clientId: Long,
        @Query("toBoardID") toBoardId: Int
    ) : Response<ResponseBody>

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

    @GET("api/v1/teachers")
    suspend fun getAllTeachers(
            @Query("branchID") branchId: Int
    ): Response<List<TeacherDTO>>

    @GET("api/v1/rooms")
    suspend fun getAllRooms(
            @Query("branchID") branchId: Int
    ): Response<List<RoomDTO>>

    @GET("api/v1/courses")
    suspend fun getAllCourses(): Response<List<CourseDTO>>

    @POST("api/v1/client-boards")
    suspend fun createBoard(
            @Body newBoard: PostNewBoard
    ):Response<ResponseBody>

    @POST("api/v1/groups")
    suspend fun createGroup(
            @Query("branchID") branchId:Int,
            @Body newGroup: PostGroup
    ): Response<ResponseBody>

    @POST("api/v1/auth/reset")
    suspend fun getRecoveryCode(
        @Query("email") email: String
    ): Response<ResponseBody>

    @GET("api/v1/students/without_groups")
    suspend fun getStudentsWoutGroups(
            @Query("branchID") branchId: Int
    ): Response<List<StudentsDTO>>

    @PUT("api/v1/students/add_in_group/{id}")
    suspend fun addStudentToGroup(
            @Path("id") id: Long,
            @Query("branchID") branchId: Int,
            @Body student:StudentsDTO
    ): Response<ResponseBody>
}