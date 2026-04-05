package br.com.droidchat.ui.feature.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.droidchat.R
import br.com.droidchat.ui.components.PrimaryButton
import br.com.droidchat.ui.components.ProfilePictureOptionsModalBottomSheet
import br.com.droidchat.ui.components.ProfilePictureSelector
import br.com.droidchat.ui.components.SecondaryTextField
import br.com.droidchat.ui.theme.BackgroundGradient
import br.com.droidchat.ui.theme.DroidChatTheme
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute(
    viewModel: SignUpViewModel = viewModel()
) {
    val state by viewModel.formState.collectAsState()
    SignUpScreen(
        state = state,
        uiEvent = viewModel::onUiEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpScreen(
    state: SignUpFormState,
    uiEvent: (SignUpFormEvent) -> Unit
) {
    val sheetState: SheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .background(BackgroundGradient)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(56.dp))

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null
            )

            Spacer(Modifier.height(16.dp))

            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp)
                ),
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProfilePictureSelector(
                        imageUri = state.profilePictureUri
                    ) {
                        uiEvent(SignUpFormEvent.ShowBottomSheet)
                    }

                    Spacer(Modifier.height(24.dp))

                    SecondaryTextField(
                        value = state.firstName,
                        placeHolder = stringResource(R.string.feature_sign_up_first_name),
                        onValueChanged = { value ->
                            uiEvent(
                                SignUpFormEvent.OnFieldChanged(
                                    type = SignUpFormFieldType.NAME,
                                    value = value
                                )
                            )
                        }
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        value = state.lastName,
                        placeHolder = stringResource(R.string.feature_sign_up_last_name),
                        onValueChanged = { value ->
                            uiEvent(
                                SignUpFormEvent.OnFieldChanged(
                                    type = SignUpFormFieldType.LAST_NAME,
                                    value = value
                                )
                            )
                        }
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        value = state.email,
                        placeHolder = stringResource(R.string.feature_sign_up_email),
                        onValueChanged = { value ->
                            uiEvent(
                                SignUpFormEvent.OnFieldChanged(
                                    type = SignUpFormFieldType.EMAIL,
                                    value = value
                                )
                            )
                        },
                        keyboardType = KeyboardType.Email
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        value = state.password,
                        placeHolder = stringResource(R.string.feature_sign_up_password),
                        onValueChanged = { value ->
                            uiEvent(
                                SignUpFormEvent.OnFieldChanged(
                                    type = SignUpFormFieldType.PASSWORD,
                                    value = value
                                )
                            )
                        },
                        keyboardType = KeyboardType.Password
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        value = state.passwordConfirmation,
                        placeHolder = stringResource(R.string.feature_sign_up_password_confirmation),
                        onValueChanged = { value ->
                            uiEvent(
                                SignUpFormEvent.OnFieldChanged(
                                    type = SignUpFormFieldType.PASSWORD_CONFIRMATION,
                                    value = value
                                )
                            )
                        },
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )

                    Spacer(Modifier.height(36.dp))

                    PrimaryButton(
                        text = stringResource(R.string.feature_sign_up_button),
                        isLoading = state.isLoading,
                        onClick = {
                            uiEvent(SignUpFormEvent.Submit)
                        }
                    )
                }
            }

            if (state.shouldShowProfilePictureBottomSheet) {
                ProfilePictureOptionsModalBottomSheet(
                    onPictureSelected = { uri ->
                        uiEvent(SignUpFormEvent.ProfilePhotoChanged(uri))
                        coroutineScope
                            .launch { sheetState.hide() }
                            .invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    uiEvent(SignUpFormEvent.HideBottomSheet)
                                }
                            }
                    },
                    onDismissRequest = { uiEvent(SignUpFormEvent.HideBottomSheet) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    DroidChatTheme {
        SignUpScreen(
            state = SignUpFormState(),
            uiEvent = {}
        )
    }
}
