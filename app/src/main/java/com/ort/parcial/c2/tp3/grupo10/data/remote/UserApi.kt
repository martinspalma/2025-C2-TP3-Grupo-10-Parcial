package com.ort.parcial.c2.tp3.grupo10.data.remote

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.Response

// Modelos para el JSON del endpoint /users/{id}
data class Geolocation(
    val lat: String,
    val long: String
)

data class Address(
    val geolocation: Geolocation,
    val city: String,
    val street: String,
    val number: Int,
    val zipcode: String
)

data class Name(
    val firstname: String,
    val lastname: String
)

data class UserResponse(
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val name: Name,
    val address: Address,
    val phone: String,
    val __v: Int
)

interface UserApi {
    @Headers("x-api-key: 123456789")
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<UserResponse>
}
