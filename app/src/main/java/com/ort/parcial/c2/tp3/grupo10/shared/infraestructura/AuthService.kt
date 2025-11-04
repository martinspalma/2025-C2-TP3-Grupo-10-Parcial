package com.ort.parcial.c2.tp3.grupo10.shared.infraestructura

import com.ort.parcial.c2.tp3.grupo10.shared.infraestructura.authservice.model.LoginResponse
import com.ort.parcial.c2.tp3.grupo10.shared.infraestructura.authservice.model.SignUpResponse

interface AuthService {

    suspend fun login() : LoginResponse;
    suspend fun signUp(): SignUpResponse
}