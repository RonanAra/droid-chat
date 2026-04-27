package br.com.droidchat.ui.feature.signup

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.droidchat.R
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
class SignUpViewModel @Inject constructor(
    private val formValidator: FormValidator<SignUpFormState>,
    private val repository: AuthRepository
) : ViewModel() {

    private val _formState = MutableStateFlow(SignUpFormState())
    val formState: StateFlow<SignUpFormState> = _formState.asStateFlow()

    fun onUiEvent(event: SignUpFormEvent) {
        when (event) {
            is SignUpFormEvent.OnFieldChanged -> onFormFieldChanged(
                value = event.value,
                type = event.type
            )
            SignUpFormEvent.Submit -> doSignUp()
            SignUpFormEvent.ShowBottomSheet -> showProfilePictureBottomSheet(true)
            SignUpFormEvent.HideBottomSheet -> showProfilePictureBottomSheet(false)
            is SignUpFormEvent.ProfilePhotoChanged -> onProfilePhotoChanged(event.uri)
        }
    }

    private fun onProfilePhotoChanged(uri: Uri) {
        _formState.update { it.copy(profilePictureUri = uri) }
    }

    private fun onFormFieldChanged(
        value: String,
        type: SignUpFormFieldType
    ) {
        when (type) {
            SignUpFormFieldType.EMAIL -> _formState.update { state ->
                state.copy(email = value, emailError = null)
            }

            SignUpFormFieldType.NAME -> _formState.update { state ->
                state.copy(firstName = value, firstNameError = null)
            }

            SignUpFormFieldType.LAST_NAME -> _formState.update { state ->
                state.copy(lastName = value, lastNameError = null)
            }

            SignUpFormFieldType.PASSWORD -> {
                _formState.update { state ->
                    state.copy(password = value, passwordError = null)
                }
                updatePasswordExtraText()
            }

            SignUpFormFieldType.PASSWORD_CONFIRMATION -> {
                _formState.update { state ->
                    state.copy(passwordConfirmation = value, passwordConfirmationError = null)
                }
                updatePasswordExtraText()
            }
        }
    }

    private fun doSignUp() {
        if (isValidForm()) {
            viewModelScope.launch {
                _formState.update { it.copy(isLoading = true) }
                // Call api to Do signUp
                delay(1500L)
                _formState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun isValidForm(): Boolean {
        return formValidator.validate(_formState.value).also { state ->
            _formState.update { state }
        }.hasError.not()
    }

    private fun updatePasswordExtraText() {
        _formState.update { state ->
            state.copy(
                passwordExtraText = if (state.password.isNotEmpty() &&
                    state.password == state.passwordConfirmation
                ) {
                    R.string.feature_sign_up_passwords_match
                } else null
            )
        }
    }

    private fun showProfilePictureBottomSheet(visibility: Boolean) {
        _formState.update { it.copy(shouldShowProfilePictureBottomSheet = visibility) }
    }
}