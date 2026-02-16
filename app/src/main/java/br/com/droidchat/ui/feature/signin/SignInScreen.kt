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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.droidchat.R
import br.com.droidchat.ui.components.PrimaryButton
import br.com.droidchat.ui.components.PrimaryTextField
import br.com.droidchat.ui.theme.BackgroundGradient
import br.com.droidchat.ui.theme.Dimens
import br.com.droidchat.ui.theme.DroidChatTheme

@Composable
fun SignInRoute() {
    SignInScreen()
}

@Composable
private fun SignInScreen() {
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

        var email by remember { mutableStateOf("") }

        PrimaryTextField(
            value = email,
            onValueChanged = { email = it },
            keyboardType = KeyboardType.Email,
            placeHolder = stringResource(R.string.feature_login_email),
            leadingIconRes = R.drawable.ic_envelope
        )

        Spacer(Modifier.height(14.dp))

        var password by remember { mutableStateOf("") }

        PrimaryTextField(
            value = password,
            onValueChanged = { password = it },
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            placeHolder = stringResource(R.string.feature_login_password),
            leadingIconRes = R.drawable.ic_lock
        )

        Spacer(Modifier.height(98.dp))

        var isLoading by remember { mutableStateOf(false) }

        PrimaryButton(
            isLoading = isLoading,
            text = stringResource(R.string.feature_login_button),
            onClick = {
                isLoading = !isLoading
            }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    DroidChatTheme {
        SignInScreen()
    }
}