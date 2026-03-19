package br.com.droidchat.ui.feature.signup

import android.net.Uri
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import br.com.droidchat.ui.components.ProfilePictureSelector
import br.com.droidchat.ui.components.SecondaryTextField
import br.com.droidchat.ui.theme.BackgroundGradient
import br.com.droidchat.ui.theme.DroidChatTheme

@Composable
fun SignUpRoute() {
    SignUpScreen()
}

@Composable
private fun SignUpScreen() {
    var profilePictureSelectedUri by remember {
        mutableStateOf<Uri?>(null)
    }

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
                        imageUri = profilePictureSelectedUri
                    ) { }

                    Spacer(Modifier.height(24.dp))

                    SecondaryTextField(
                        value = "",
                        placeHolder = stringResource(R.string.feature_sign_up_first_name),
                        onValueChanged = {}
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        value = "",
                        placeHolder = stringResource(R.string.feature_sign_up_last_name),
                        onValueChanged = {}
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        value = "",
                        placeHolder = stringResource(R.string.feature_sign_up_email),
                        onValueChanged = {},
                        keyboardType = KeyboardType.Email
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        value = "",
                        placeHolder = stringResource(R.string.feature_sign_up_password),
                        onValueChanged = {},
                        keyboardType = KeyboardType.Password
                    )

                    Spacer(Modifier.height(22.dp))

                    SecondaryTextField(
                        value = "",
                        placeHolder = stringResource(R.string.feature_sign_up_password_confirmation),
                        onValueChanged = {},
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )

                    Spacer(Modifier.height(36.dp))

                    PrimaryButton(
                        text = stringResource(R.string.feature_sign_up_button),
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    DroidChatTheme {
        SignUpScreen()
    }
}
