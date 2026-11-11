package com.example.booky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.booky.ui.pages.BookDetailScreen
import com.example.booky.ui.pages.BookItem
import com.example.booky.ui.pages.BookListScreen
import com.example.booky.ui.theme.BookyTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

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

    // FAB
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Acción de la cámara */ },
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_photo_camera_24),
                    contentDescription = stringResource(R.string.search_by_photo)
                )
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "bookList",
            modifier = Modifier.padding(innerPadding)
        ) {
            // Lista de libros
            composable("bookList") {
                var searchQuery by remember { mutableStateOf("") }

                val sampleBooks = listOf(
                    BookItem(
                        1,
                        "El pozo de la ascensión",
                        "Brandon Sanderson",
                        R.drawable.pozo_ascension
                    ),
                    BookItem(2, "Corona de medianoche", "Sarah J. Maas", R.drawable.tog),
                    BookItem(3, "Han cantado Bingo", "Lana Corujo", R.drawable.han_cantando_bingo)
                )

                BookListScreen(
                    title = stringResource(R.string.pending_readings),
                    books = sampleBooks,
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    onBookClick = { book ->
                        navController.navigate("bookDetail/${book.title}")
                    },
                    onCameraClick = { }
                )
            }

            // Pantalla de detalle del libro
            composable(
                route = "bookDetail/{bookTitle}",
                arguments = listOf(navArgument("bookTitle") { type = NavType.StringType })
            ) { backStackEntry ->
                val bookTitle = backStackEntry.arguments?.getString("bookTitle") ?: "Libro"

                var reviewTextState by remember { mutableStateOf("") }

                BookDetailScreen(
                    title = bookTitle,
                    reviewText = reviewTextState,
                    onReviewChanged = { newText ->
                        reviewTextState = newText
                    }
                )
            }
        }
    }
}

