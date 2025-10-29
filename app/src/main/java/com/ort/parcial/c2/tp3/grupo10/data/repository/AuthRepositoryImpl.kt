package com.ort.parcial.c2.tp3.grupo10.data.repository

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ort.parcial.c2.tp3.grupo10.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) : AuthRepository {

    override suspend fun register(name: String, email: String, password: String): Result<Unit> = runCatching {
        val res = auth.createUserWithEmailAndPassword(email, password).await()
        val uid = res.user?.uid ?: throw IllegalStateException("Usuario sin UID")
        val data = mapOf(
            "name" to name,
            "email" to email,
            "createdAt" to Timestamp.now()
        )
        try {
            withTimeout(8000) {
                db.collection("users").document(uid).set(data).await()
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Error guardando usuario en Firestore", e)
            throw IllegalStateException(
                "No se pudo guardar el usuario en Firestore. ¿Está creada la base de datos del proyecto en Firebase? ${e.message}"
            )
        }
    }

    override suspend fun login(email: String, password: String): Result<Unit> = runCatching {
        auth.signInWithEmailAndPassword(email, password).await()
        val uid = auth.currentUser?.uid ?: return@runCatching
        try {
            withTimeout(5000) {
                val userDoc = db.collection("users").document(uid).get().await()
                if (!userDoc.exists()) {
                    val data = mapOf("email" to email, "createdAt" to Timestamp.now())
                    db.collection("users").document(uid).set(data).await()
                }
            }
        } catch (e: Exception) {
            Log.w("AuthRepository", "Login ok pero no se pudo sincronizar perfil en Firestore", e)
            // No bloqueamos el login si Firestore falla; ya hay sesión.
        }
    }

    override fun currentUserId(): String? = auth.currentUser?.uid

    override fun signOut() {
        auth.signOut()
    }
}
