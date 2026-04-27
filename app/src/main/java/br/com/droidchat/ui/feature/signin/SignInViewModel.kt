package br.com.droidchat.ui.feature.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.droidchat.data.repository.AuthRepository
import br.com.droidchat.ui.validator.FormValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val formValidator: FormValidator<SignInFormState>,
    private val repository: AuthRepository
) : ViewModel() {

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
        return formValidator.validate(_formState.value).also { state ->
            _formState.update { state }
        }.hasError.not()
    }
}