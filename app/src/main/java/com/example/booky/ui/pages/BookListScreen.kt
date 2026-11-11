package com.example.booky.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.booky.ui.components.BookCard
import com.example.booky.R
import com.example.booky.ui.components.BookCardUiState
import com.example.booky.ui.components.BookSearchBar
import com.example.booky.ui.components.SearchBarUiState
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
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onBookClick: (BookItem) -> Unit,
    onCameraClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val filteredBooks = books.filter {
        it.title.contains(searchQuery, ignoreCase = true) ||
                it.author.contains(searchQuery, ignoreCase = true)
    }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            //Encabezado superior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(vertical = 18.dp, horizontal = 16.dp),
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
            BookSearchBar(
                state = SearchBarUiState(
                    query = searchQuery,
                    onQueryChange = onSearchQueryChange
                ),
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            //Cuadrícula de libros
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredBooks) { book ->
                    BookCard(
                        state = BookCardUiState(
                            id = book.id,
                            title = book.title,
                            author = book.author,
                            imageRes = book.imageRes,
                            onMarkAsReadClick = { onBookClick(book) }
                        )
                    )
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
            searchQuery = "",
            onSearchQueryChange = {},
            onBookClick = {},
            onCameraClick = {}
        )
    }
}