package com.example.booky.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.booky.ui.components.BookCard
import androidx.compose.ui.text.font.Font
import com.example.booky.R
import com.example.booky.ui.components.BodoniFLF
import com.example.booky.ui.components.Poppins

// MODELO DE DATOS
data class BookItem(
    val id: Int,
    val title: String,
    val author: String,
    val imageRes: Int
)

//Paleta de colores
private val BookyBackground = Color(0xFF272324)        // Fondo oscuro de la app
private val BookyHeaderBg = Color(0xFFBFA484)          // Fondo beige del encabezado
private val BookyHeaderTitle = Color(0xFF582336)       // Color del texto del título
private val BookySearchBarBg = Color(0xFFC8D1D9)       // Color de la barra de búsqueda
private val BookySearchText = Color(0xFF272324)        // Texto de la barra de búsqueda
private val BookySearchPlaceholder = Color(0xFF6B6566) // Texto de "Buscar..."
private val BookyTextColor = Color(0xFFE1BC9E)         // Letras de título y autor
private val BookyFabBg = Color(0xFF582336)             // Color del botón flotante


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    title: String = "Lecturas\nPendientes",
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
        containerColor = BookyBackground,
        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                FloatingActionButton(
                    onClick = { /*Por añadir*/ },
                    containerColor = BookyFabBg,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier.size(64.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PhotoCamera,
                        contentDescription = "Buscar por foto",
                        modifier = Modifier.size(28.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Buscar por foto",
                    color = BookyTextColor,
                    fontFamily = Poppins,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(BookyBackground)
        ) {
            //Encabezado superior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
                    .background(BookyHeaderBg)
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = BookyHeaderTitle,
                    fontSize = 33.sp,
                    fontFamily = BodoniFLF,
                    fontWeight = FontWeight.Bold,
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
                        color = BookySearchPlaceholder,
                        fontSize = 15.sp,
                        fontFamily = Poppins
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = BookySearchPlaceholder
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(26.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = BookySearchBarBg,
                    unfocusedContainerColor = BookySearchBarBg,
                    disabledContainerColor = BookySearchBarBg,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = BookySearchText,
                    unfocusedTextColor = BookySearchText
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
@Preview(showBackground = true, backgroundColor = 0xFF272324)
@Composable
fun BookListScreenPreview() {
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