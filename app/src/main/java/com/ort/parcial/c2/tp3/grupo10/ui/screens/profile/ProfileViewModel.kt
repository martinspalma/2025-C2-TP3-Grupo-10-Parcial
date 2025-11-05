package com.ort.parcial.c2.tp3.grupo10.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ort.parcial.c2.tp3.grupo10.data.remote.UserApi
import com.ort.parcial.c2.tp3.grupo10.data.remote.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// 1. (MEJORA) Modelar todos los posibles estados de la UI.
sealed class UserProfileUiState {
    /** El estado inicial, mientras se cargan los datos. */
    data object Loading : UserProfileUiState()
    /** El estado de éxito, cuando los datos se han cargado correctamente. */
    data class Success(val user: UserResponse) : UserProfileUiState()
    /** El estado de error, si algo falló durante la carga. */
    data class Error(val message: String) : UserProfileUiState()
}

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userApi: UserApi
) : ViewModel() {

    // 2. (MEJORA) Usar StateFlow y un "backing property" para el estado.
    // '_uiState' es privado y mutable, solo el ViewModel puede cambiarlo.
    private val _uiState = MutableStateFlow<UserProfileUiState>(UserProfileUiState.Loading)

    // 'uiState' es público y de solo lectura. La UI lo observa para actualizarse.
    val uiState = _uiState.asStateFlow()

    init {
        // El ID del usuario podría venir de un gestor de sesión, pero para el ejemplo usamos 1.
        fetchUser(1)
    }

    fun fetchUser(id: Int) {
        // Se asegura que la corrutina se lance en el scope del ViewModel.
        viewModelScope.launch {
            // Inicia en estado de carga. La UI mostrará un spinner.
            _uiState.value = UserProfileUiState.Loading

            try {
                // 3. (MEJORA) Llamada a la API dentro de un bloque try/catch.
                val response = userApi.getUserById(id)

                if (response.isSuccessful && response.body() != null) {
                    // Si la respuesta es exitosa y tiene cuerpo, pasamos al estado Success.
                    _uiState.value = UserProfileUiState.Success(response.body()!!)
                } else {
                    // Si el servidor responde con un error (ej: 404, 500).
                    _uiState.value = UserProfileUiState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                // Si hay una excepción (ej: no hay conexión a internet).
                _uiState.value = UserProfileUiState.Error("An error occurred: ${e.message}")
            }
        }
    }
}
