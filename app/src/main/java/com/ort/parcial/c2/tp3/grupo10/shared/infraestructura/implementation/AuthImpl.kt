package com.ort.parcial.c2.tp3.grupo10.shared.infraestructura.implementation

import com.ort.parcial.c2.tp3.grupo10.shared.infraestructura.AuthService
import com.ort.parcial.c2.tp3.grupo10.shared.infraestructura.authservice.AuthApi
import com.ort.parcial.c2.tp3.grupo10.shared.infraestructura.authservice.model.LoginResponse
import javax.inject.Inject

class AuthImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthService {

    override suspend fun login(): LoginResponse {
        val response = authApi.authLogin()
        return if (response.isSuccessful) {
            response.body() ?: throw Exception("Respuesta vacía")
        } else {
            throw Exception("Error al logearse: ${response.code()} ${response.message()}")
        }
    }
}