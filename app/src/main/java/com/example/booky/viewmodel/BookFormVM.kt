package com.example.booky.viewmodel

import android.util.Patterns
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.example.booky.R
import com.example.booky.seeder.GeneroLibro
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class BookFormUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val genero: GeneroLibro = GeneroLibro.FANTASIA,
    val expanded: Boolean = false,
    val aceptaTerminos: Boolean = false,
    val emailTouched: Boolean = false,
    val passwordTouched: Boolean = false,
    val terminosTouched: Boolean = false
) {
    @get:StringRes
    private val emailError: Int?
        get() = when {
            email.isBlank() -> R.string.error_correo_vacio
            !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() -> R.string.error_correo_invalido
            else -> null
        }

    @get:StringRes
    private val passwordError: Int?
        get() = when {
            password.isBlank() -> R.string.error_contrasena_vacia
            password.length < 8 ||
                    !password.any { it.isUpperCase() } ||
                    !password.any { !it.isLetterOrDigit() } -> R.string.error_contrasena
            else -> null
        }

    @get:StringRes
    private val terminosError: Int?
        get() = if (!aceptaTerminos) R.string.error_terminos_no_aceptados else null

    @get:StringRes
    val errorEmail: Int? get() = if (emailTouched) emailError else null

    @get:StringRes
    val errorPassword: Int? get() = if (passwordTouched) passwordError else null

    @get:StringRes
    val errorTerminos: Int? get() = if (terminosTouched) terminosError else null

    val isValid: Boolean
        get() = emailError == null && passwordError == null && terminosError == null
}

class BookFormVM : ViewModel() {

    private val _uiState = MutableStateFlow(BookFormUiState())
    val uiState: StateFlow<BookFormUiState> = _uiState.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _uiState.update { it.copy(email = newEmail, emailTouched = true) }
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.update { it.copy(password = newPassword, passwordTouched = true) }
    }

    fun onTogglePasswordVisibility() {
        _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    fun onExpandedChange(expanded: Boolean) {
        _uiState.update { it.copy(expanded = expanded) }
    }

    fun onGeneroChange(newGenero: GeneroLibro) {
        _uiState.update { it.copy(genero = newGenero, expanded = false) }
    }

    fun onTerminosChange(aceptado: Boolean) {
        _uiState.update { it.copy(aceptaTerminos = aceptado, terminosTouched = true) }
    }

    fun onSave(): Boolean {
        val current = _uiState.value
        if (!current.isValid) {
            _uiState.update {
                it.copy(
                    emailTouched = true,
                    passwordTouched = true,
                    terminosTouched = true
                )
            }
            return false
        }

        return true
    }
}
