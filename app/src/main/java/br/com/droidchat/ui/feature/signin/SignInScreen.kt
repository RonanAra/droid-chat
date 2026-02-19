package br.com.droidchat.ui.feature.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import br.com.droidchat.ui.components.PrimaryTextField
import br.com.droidchat.ui.theme.BackgroundGradient
import br.com.droidchat.ui.theme.Dimens
import br.com.droidchat.ui.theme.DroidChatTheme

@Composable
fun SignInRoute(
    viewModel: SignInViewModel = viewModel()
) {
    val uiState by viewModel.formState.collectAsState()

    SignInScreen(
        formState = uiState,
        formEvent = viewModel::onUiEvent
    )
}

@Composable
private fun SignInScreen(
    formState: SignInFormState,
    formEvent: SignInFormEventInvoke
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundGradient)
            .padding(Dimens.medium)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null
        )

        Spacer(Modifier.height(78.dp))

        PrimaryTextField(
            value = formState.email,
            onValueChanged = {
                formEvent(SignInFormEvent.EmailChanged(it))
            },
            keyboardType = KeyboardType.Email,
            placeHolder = stringResource(R.string.feature_login_email),
            leadingIconRes = R.drawable.ic_envelope,
            errorMessage = formState.emailError?.let { stringResource(it) }
        )

        Spacer(Modifier.height(14.dp))

        PrimaryTextField(
            value = formState.password,
            onValueChanged = {
                formEvent(SignInFormEvent.PasswordChanged(it))
            },
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            placeHolder = stringResource(R.string.feature_login_password),
            leadingIconRes = R.drawable.ic_lock,
            errorMessage = formState.passwordError?.let { stringResource(it) }
        )

        Spacer(Modifier.height(98.dp))

        PrimaryButton(
            isLoading = formState.isLoading,
            text = stringResource(R.string.feature_login_button),
            onClick = {
                formEvent(SignInFormEvent.Submit)
            }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    DroidChatTheme {
        SignInScreen(
            formState = SignInFormState(),
            formEvent = {}
        )
    }
}