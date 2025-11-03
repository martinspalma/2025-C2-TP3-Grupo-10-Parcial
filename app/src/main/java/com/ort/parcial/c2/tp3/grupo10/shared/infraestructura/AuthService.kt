package com.ort.parcial.c2.tp3.grupo10.shared.infraestructura

import com.ort.parcial.c2.tp3.grupo10.shared.infraestructura.authservice.model.LoginResponse

interface AuthService {

    suspend fun login() : LoginResponse;
}