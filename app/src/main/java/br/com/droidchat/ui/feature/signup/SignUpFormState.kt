package br.com.droidchat.ui.feature.signup

import android.net.Uri
import androidx.annotation.StringRes

data class SignUpFormState(
    val profilePictureUri: Uri? = null,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val passwordConfirmation: String = "",
    val isLoading: Boolean = false,
    val shouldShowProfilePictureBottomSheet: Boolean = false,
    @get:StringRes val firstNameError: Int? = null,
    @get:StringRes val lastNameError: Int? = null,
    @get:StringRes val passwordError: Int? = null,
    @get:StringRes val emailError: Int? = null,
)
