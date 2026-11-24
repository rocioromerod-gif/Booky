package com.example.booky.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.booky.R
import kotlinx.coroutines.launch

private data class BottomDestination(
    @StringRes val labelRes: Int,
    val icon: ImageVector
)

private val destinations = listOf(
    BottomDestination(R.string.todos_los_libros, Icons.Filled.Book),
    BottomDestination(R.string.libros_favoritos, Icons.Filled.Favorite),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    onCameraClick: () -> Unit = {},
    contenido: @Composable () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val okLabel = stringResource(R.string.ok)
    val versionMessage = stringResource(R.string.version)
    var selected by rememberSaveable { mutableIntStateOf(0) }


    val showSnackbar: (String) -> Unit = { message ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            val result = snackbarHostState.showSnackbar(
                message = message,
                actionLabel = okLabel,
                duration = SnackbarDuration.Short
            )
            if (result == SnackbarResult.ActionPerformed) {
                // TODO: acción del snackbar
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(destinations[selected].labelRes)) },
                actions = {
                    IconButton(onClick = { showSnackbar(versionMessage) }) {
                        Icon(
                            imageVector = Icons.Filled.Book,
                            contentDescription = stringResource(R.string.version)
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                destinations.forEachIndexed { index, destination ->

                    val labelText = stringResource(id = destination.labelRes)
                    val snackbarMessage = stringResource(id = R.string.tab_snackbar, labelText)

                    NavigationBarItem(
                        selected = selected == index,
                        onClick = {
                            selected = index
                            showSnackbar(snackbarMessage)
                        },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = stringResource(destination.labelRes)
                            )
                        },
                        label = { Text(stringResource(destination.labelRes)) }
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            CameraButton(
                state = CameraButtonUIState(
                    onClick = onCameraClick
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            contenido()
        }
    }
}

