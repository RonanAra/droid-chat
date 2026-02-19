package br.com.droidchat.ui.feature.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.droidchat.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _formState = MutableStateFlow(SignInFormState())
    val formState: StateFlow<SignInFormState> = _formState.asStateFlow()

    fun onUiEvent(event: SignInFormEvent) {
        when (event) {
            is SignInFormEvent.EmailChanged -> onEmailChanged(event.email)
            is SignInFormEvent.PasswordChanged -> onPasswordChanged(event.password)
            SignInFormEvent.Submit -> doLogin()
        }
    }

    private fun onEmailChanged(email: String) {
        _formState.update { state ->
            state.copy(email = email, emailError = null)
        }
    }

    private fun onPasswordChanged(password: String) {
        _formState.update { state ->
            state.copy(password = password, passwordError = null)
        }
    }

    private fun doLogin() {
        // Replace this with real impl
        if (validateFields()) {
            viewModelScope.launch {
                _formState.update { state ->
                    state.copy(isLoading = true)
                }

                delay(3000L)

                _formState.update { state ->
                    state.copy(isLoading = false)
                }
            }
        }
    }

    private fun validateFields(): Boolean {
        if (formState.value.email.isBlank()) {
            _formState.update { it.copy(emailError = R.string.error_message_email_invalid) }
            return false
        }

        if (formState.value.password.isBlank()) {
            _formState.update { it.copy(passwordError = R.string.error_message_password_invalid) }
            return false
        }
        return true
    }
}