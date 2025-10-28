package com.ort.parcial.c2.tp3.grupo10.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ort.parcial.c2.tp3.grupo10.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false,
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(AuthUiState())
    val state: StateFlow<AuthUiState> = _state

    fun updateName(v: String) { _state.value = _state.value.copy(name = v) }
    fun updateEmail(v: String) { _state.value = _state.value.copy(email = v) }
    fun updatePassword(v: String) { _state.value = _state.value.copy(password = v) }

    fun register(onSuccess: () -> Unit) = viewModelScope.launch {
        val s = _state.value
        _state.value = s.copy(loading = true, error = null, success = false)
        val res = repo.register(s.name.trim(), s.email.trim(), s.password)
        _state.value = if (res.isSuccess) {
            s.copy(loading = false, error = null, success = true)
        } else {
            s.copy(loading = false, error = res.exceptionOrNull()?.message ?: "Error", success = false)
        }
        if (res.isSuccess) onSuccess()
    }

    fun login(onSuccess: () -> Unit) = viewModelScope.launch {
        val s = _state.value
        _state.value = s.copy(loading = true, error = null, success = false)
        val res = repo.login(s.email.trim(), s.password)
        _state.value = if (res.isSuccess) {
            s.copy(loading = false, error = null, success = true)
        } else {
            s.copy(loading = false, error = res.exceptionOrNull()?.message ?: "Error", success = false)
        }
        if (res.isSuccess) onSuccess()
    }
}

