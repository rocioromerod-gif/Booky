package com.example.booky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.booky.ui.components.CameraButton
import com.example.booky.ui.pages.BookDetailScreen
import com.example.booky.ui.pages.BookListScreen
import com.example.booky.ui.pages.BookyDetailData
import com.example.booky.ui.theme.BookyTheme
import com.example.booky.viewmodel.BookyDetailVMl
import androidx.compose.runtime.getValue
import com.example.booky.ui.components.CameraButtonUIState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BookyNavGraph()
                }
            }
        }
    }
}

@Composable
fun BookyNavGraph() {
    val navController = rememberNavController()

    Scaffold(
        floatingActionButton = {
            CameraButton(
                state = CameraButtonUIState(
                    onClick = { /* Acción de la cámara */ }
                )
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "listaLibros",
            modifier = Modifier.padding(innerPadding)
        ) {
            // Lista de libros
            composable("listaLibros") {
                BookListScreen(
                    onNavigateToDetail = { bookId ->
                        navController.navigate("bookDetail/$bookId")
                    },
                    modifier = Modifier.fillMaxSize(),
                )
            }

            // Pantalla de detalle del libro
            composable(
                route = "bookDetail/{bookId}",
                arguments = listOf(navArgument("bookId") { type = NavType.IntType })
            ) { backStackEntry ->
                val bookId = backStackEntry.arguments?.getInt("bookId") ?: 0
                val vm: BookyDetailVMl = viewModel()
                val state by vm.uiState.collectAsState()

                LaunchedEffect(bookId) {
                    vm.load(bookId)
                }

                val libro = state.libro
                val detailData = BookyDetailData(
                    id = libro?.id ?: bookId,
                    imagen = libro?.imagen ?: R.drawable.han_cantando_bingo,
                    titulo = libro?.titulo ?: stringResource(R.string.titulo),
                    autor = libro?.autor ?: stringResource(R.string.autor),
                    reseña = state.textoReview,
                    estrellas = 4
                )

                BookDetailScreen(
                    data = detailData,
                    modifier = Modifier.fillMaxSize(),
                    onReviewChanged = { newText -> vm.onReviewChanged(newText) }
                )
            }
        }
    }
}


