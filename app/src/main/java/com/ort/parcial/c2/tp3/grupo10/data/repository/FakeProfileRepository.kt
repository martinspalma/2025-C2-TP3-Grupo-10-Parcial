package com.ort.parcial.c2.tp3.grupo10.data.repository

import com.ort.parcial.c2.tp3.grupo10.data.remote.ProfileApi
import com.ort.parcial.c2.tp3.grupo10.data.remote.ProfileResponse
import com.ort.parcial.c2.tp3.grupo10.data.remote.ProfileUpdateRequest

class FakeProfileRepository : ProfileApi {
    // Simulación de base de datos en memoria
    private val profiles = mutableMapOf<Int, ProfileResponse>(
        1 to ProfileResponse(1, "mockuser", "123456789", "mock@email.com"),
        2 to ProfileResponse(2, "anotheruser", "987654321", "another@email.com")
    )

    override suspend fun getProfileById(id: Int): ProfileResponse {
        return profiles[id] ?: throw Exception("Perfil no encontrado")
    }

    override suspend fun updateProfile(id: Int, profile: ProfileUpdateRequest): ProfileResponse {
        val updated = ProfileResponse(id, profile.username, profile.phone, profile.email)
        profiles[id] = updated
        return updated
    }
}
