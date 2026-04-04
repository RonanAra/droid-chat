@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.droidchat.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import br.com.droidchat.DroidChatFileProvider
import br.com.droidchat.R
import br.com.droidchat.ui.theme.DroidChatTheme

@Composable
fun ProfilePictureOptionsModalBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    onPictureSelected: (Uri) -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    val context = LocalContext.current
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { onPictureSelected(it) }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
           photoUri?.let { onPictureSelected(it) }
        }
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.onSurface,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        ProfilePictureOption(
            iconRes = R.drawable.ic_photo_camera,
            labelRes = R.string.common_take_photo,
            onClick = {
                photoUri = DroidChatFileProvider.getImageUri(context.applicationContext)
                photoUri?.let { cameraLauncher.launch(it) }
            }
        )

        Spacer(Modifier.height(12.dp))

        ProfilePictureOption(
            iconRes = R.drawable.ic_photo_library,
            labelRes = R.string.common_upload_photo,
            onClick = {
                imagePicker.launch("image/*")
            }
        )

        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun ProfilePictureOption(
    @DrawableRes iconRes: Int,
    @StringRes labelRes: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(48.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null
        )

        Spacer(Modifier.width(4.dp))

        Text(
            text = stringResource(labelRes),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview
@Composable
private fun Preview() {
    val sheetState = SheetState(
        skipPartiallyExpanded = false,
        density = Density(LocalContext.current),
        initialValue = SheetValue.Expanded
    )
    DroidChatTheme {
        ProfilePictureOptionsModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {},
            onPictureSelected = {}
        )
    }
}