package br.com.droidchat.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.droidchat.R
import br.com.droidchat.ui.extension.bottomBorder
import br.com.droidchat.ui.theme.ColorSuccess
import br.com.droidchat.ui.theme.DroidChatTheme

@Composable
fun SecondaryTextField(
    value: String,
    placeHolder: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    extraText: String? = null,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Unspecified
) {
    var inputText by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val visibilityIcon = if (passwordVisible) {
        R.drawable.ic_visibility
    } else R.drawable.ic_visibility_off

    BasicTextField(
        value = inputText,
        onValueChange = {
            inputText = it
            onValueChanged(it)
        },
        modifier = modifier.fillMaxWidth(),
        singleLine = singleLine,
        maxLines = maxLines,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            fontWeight = FontWeight.Bold
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = if (keyboardType == KeyboardType.Text) {
                KeyboardCapitalization.Sentences
            } else KeyboardCapitalization.None,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        visualTransformation = if (keyboardType == KeyboardType.Password && !passwordVisible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
    ) { innerTextField ->
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            Row(
                modifier = Modifier.bottomBorder(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    strokeWidth = 1.dp
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = placeHolder,
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(Modifier.height(4.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            innerTextField()
                        }

                        extraText?.let {
                            Text(
                                text = it,
                                modifier = Modifier.padding(4.dp),
                                color = ColorSuccess,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        if (keyboardType == KeyboardType.Password && value.isNotEmpty()) {
                            IconButton(
                                onClick = { passwordVisible = !passwordVisible }
                            ) {
                                Icon(
                                    painter = painterResource(visibilityIcon),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DroidChatTheme {
        SecondaryTextField(
            value = "",
            onValueChanged = {},
            placeHolder = "Primeiro nome",
            extraText = "as senhas sao iguais",
            keyboardType = KeyboardType.Password
        )
    }
}
