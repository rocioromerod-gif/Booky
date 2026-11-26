package com.example.booky.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.booky.R
import kotlinx.coroutines.launch

private data class BottomDestination(
    @StringRes val labelRes: Int,
    val icon: ImageVector
)

//navegaciones para la bottombar
private val destinations = listOf(
    BottomDestination(R.string.lecturas_pendientes, Icons.Filled.Book),
    BottomDestination(R.string.libros_favoritos, Icons.Filled.Favorite),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    //Parametro para que funcione la camara
    onCameraClick: () -> Unit = {},
    contenido: @Composable () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
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
                // Acción del snackbar
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        //Top bar que muestra el nombre de la app y un pequeño icono que muestra la version
        topBar = {
            TopAppBar(
                modifier = Modifier.height(52.dp),
                title = { Text(
                    text = stringResource(R.string.booky),
                    style = MaterialTheme.typography.titleSmall
                )
                        },
                actions = {
                    IconButton(onClick = { showSnackbar(versionMessage) }) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = stringResource(R.string.version)
                        )
                    }
                }
            )
        },

        //Boton de navegaciones, recorre la lista de navegaciones por indice
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
                        //Aqui muestra el icono que hayamos declarado arriba
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = stringResource(destination.labelRes)
                            )
                        },
                        //Muestra el texto declarado arriba
                        label = {
                            Text(
                            text = stringResource(destination.labelRes),
                            color = MaterialTheme.colorScheme.primary
                            ) }
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        //El FAB de la camara, por material design, lo coloca automaticamente en la esquina abajo derecha
        floatingActionButton = {
            CameraButton(
                state = CameraButtonUIState(
                    onClick = onCameraClick
                ),
                modifier = Modifier.padding(16.dp)
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

