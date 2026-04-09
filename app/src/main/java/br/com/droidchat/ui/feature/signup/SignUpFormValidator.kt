package br.com.droidchat.ui.feature.signup

import br.com.droidchat.R
import br.com.droidchat.ui.validator.EmailValidator
import br.com.droidchat.ui.validator.FormValidator
import br.com.droidchat.ui.validator.PasswordValidator

class SignUpFormValidator : FormValidator<SignUpFormState> {

    override fun validate(formState: SignUpFormState): SignUpFormState {
        val isFirstNameValid = formState.firstName.isNotEmpty()
        val isLastNameValid = formState.lastName.isNotEmpty()
        val isEmailValid = EmailValidator.isValid(formState.email)
        val isPasswordValid = PasswordValidator.isValid(formState.password)
        val isPasswordConfirmationValid = PasswordValidator.isValid(formState.passwordConfirmation)
                && formState.passwordConfirmation == formState.password

        val hasError = listOf(
            isFirstNameValid,
            isLastNameValid,
            isEmailValid,
            isPasswordValid,
            isPasswordConfirmationValid,
        ).any { !it }

        return formState.copy(
            firstNameError = R.string.error_message_field_blank.takeIf { !isFirstNameValid },
            lastNameError = R.string.error_message_field_blank.takeIf { !isLastNameValid },
            emailError = R.string.error_message_email_invalid.takeIf { !isEmailValid },
            passwordError = R.string.error_message_password_invalid.takeIf { !isPasswordValid },
            passwordConfirmationError = R.string.error_message_password_confirmation_invalid
                .takeIf { !isPasswordConfirmationValid },
            hasError = hasError,
        )
    }
}