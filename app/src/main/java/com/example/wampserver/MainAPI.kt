package com.example.wampserver

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MainAPI {
    @GET("get_all_items.php")
    suspend fun getAllUsers(): List<User>

    @POST("save_user.php")
    suspend fun saveUser(@Body user: User)

    @POST("upload_image.php")
    suspend fun uploadImage(@Body imageData: ImageData) : ImageUploadResponse
}