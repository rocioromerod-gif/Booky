package com.example.booky.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.booky.ui.components.BookCard
import com.example.booky.R
import com.example.booky.ui.theme.BookyTheme

// MODELO DE DATOS
data class BookItem(
    val id: Int,
    val title: String,
    val author: String,
    val imageRes: Int
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    title: String = stringResource(R.string.pending_readings),
    books: List<BookItem>,
    onBookClick: (BookItem) -> Unit,
    onCameraClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredBooks = books.filter {
        it.title.contains(searchQuery, ignoreCase = true) ||
                it.author.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                FloatingActionButton(
                    onClick = { /*Por añadir*/ },
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier.size(64.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.outline_photo_camera_24),
                        contentDescription = stringResource(R.string.search_by_photo),
                        modifier = Modifier.size(28.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.search_by_photo),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            //Encabezado superior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleLarge,
                    lineHeight = 36.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            //Barra de búsqueda
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = {
                    Text(
                        "Buscar...",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(26.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            //CUADRÍCULA DE LIBROS
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(filteredBooks) { book ->
                    BookCard(
                        title = book.title,
                        author = book.author,
                        imageRes = book.imageRes,
                        onMarkAsReadClick = {
                            onBookClick(book)
                        }
                    )
                }
            }
        }
    }
}

//Preview de ejemplo similar a el diseño de canva
@Preview(showBackground = true)
@Composable
fun BookListScreenPreview() {
    BookyTheme {
        val sampleBooks = listOf(
            BookItem(1, "El pozo de la ascensión", "Brandon Sanderson", R.drawable.pozo_ascension),
            BookItem(2, "Corona de medianoche", "Sarah J. Maas", R.drawable.tog)
        )

        BookListScreen(
            title = "Lecturas\nPendientes",
            books = sampleBooks,
            onBookClick = {},
            onCameraClick = {}
        )
    }
}