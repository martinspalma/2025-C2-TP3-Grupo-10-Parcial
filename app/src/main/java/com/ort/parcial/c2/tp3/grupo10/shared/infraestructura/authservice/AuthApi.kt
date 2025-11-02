package com.ort.parcial.c2.tp3.grupo10.shared.infraestructura.authservice

import com.ort.parcial.c2.tp3.grupo10.shared.infraestructura.authservice.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @Headers("x-api-key: 123456789")
    @POST("auth/login")
    suspend fun authLogin() : Response<LoginResponse>
}