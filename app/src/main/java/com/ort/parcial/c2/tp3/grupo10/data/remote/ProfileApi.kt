package com.ort.parcial.c2.tp3.grupo10.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

// Data class para el perfil (ajusta según tu modelo real)
data class ProfileUpdateRequest(
    val username: String,
    val phone: String,
    val email: String
)

data class ProfileResponse(
    val id: Int,
    val username: String,
    val phone: String,
    val email: String
)

interface ProfileApi {
    @GET("profile/{id}")
    suspend fun getProfileById(@Path("id") id: Int): ProfileResponse

    @PUT("profile/{id}")
    suspend fun updateProfile(
        @Path("id") id: Int,
        @Body profile: ProfileUpdateRequest
    ): ProfileResponse
}
