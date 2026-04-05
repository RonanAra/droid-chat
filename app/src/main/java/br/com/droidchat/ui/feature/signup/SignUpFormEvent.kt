package br.com.droidchat.ui.feature.signup

import android.net.Uri

sealed interface SignUpFormEvent {
    data class OnFieldChanged(
        val type: SignUpFormFieldType,
        val value: String
    ) : SignUpFormEvent
    data class ProfilePhotoChanged(val uri: Uri): SignUpFormEvent
    data object ShowBottomSheet : SignUpFormEvent
    data object HideBottomSheet : SignUpFormEvent
    data object Submit : SignUpFormEvent
}

enum class SignUpFormFieldType {
    EMAIL, NAME, LAST_NAME, PASSWORD,
    PASSWORD_CONFIRMATION
}
