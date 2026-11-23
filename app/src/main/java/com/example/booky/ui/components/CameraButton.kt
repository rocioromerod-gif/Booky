package com.example.booky.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booky.R

data class CameraButtonUIState(
    @DrawableRes val icono: Int = R.drawable.outline_photo_camera_24,
    val texto: String = "",
    val onClick: () -> Unit = {}
)
@Composable
fun CameraButton (
    state: CameraButtonUIState = CameraButtonUIState(),
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        FloatingActionButton(
            onClick = state.onClick,
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_photo_camera_24),
                contentDescription = stringResource(R.string.buscar_por_foto),
                modifier = Modifier
                .size(54.dp)
                .padding(12.dp),
            )
        }

        Text(
            text = stringResource(id = R.string.buscar_por_foto),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
