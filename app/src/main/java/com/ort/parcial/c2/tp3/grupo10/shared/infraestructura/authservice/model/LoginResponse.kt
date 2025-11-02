package com.ort.parcial.c2.tp3.grupo10.shared.infraestructura.authservice.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String
)