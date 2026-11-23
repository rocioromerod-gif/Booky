package com.example.booky.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.booky.ui.components.BookCard
import com.example.booky.ui.components.BookSearchBar
import com.example.booky.ui.components.SearchBarUiState
import com.example.booky.ui.theme.BookyTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booky.R
import com.example.booky.seeder.BookModel
import com.example.booky.ui.components.BookCardData
import com.example.booky.ui.components.CameraButton
import com.example.booky.ui.components.CameraButtonUIState
import com.example.booky.viewmodel.BookyListVMl




@OptIn(ExperimentalMaterial3Api::class)

var listaLibros = listOf<BookCardData>()
@Composable
fun BookListScreen(
    vm: BookyListVMl = viewModel(),
    onNavigateToDetail: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    onCameraClick: () -> Unit = {}
) {

    val state by vm.uiState.collectAsState()
    val libros = state.libros

    LaunchedEffect(Unit) {
        vm.load()
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Encabezado superior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(vertical = 30.dp, horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.lecturas_pendientes),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleLarge,
                    lineHeight = 36.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Barra de búsqueda
            BookSearchBar(
                state = SearchBarUiState(
                    query = "",
                    onQueryChange = {}
                ),
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Cuadrícula de libros
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(libros.size) { index ->
                    val libro = libros[index]
                    BookCard(
                        data = bookModel2BookCard(
                            bm = libro,
                            onMarkAsReadClick = { onNavigateToDetail(libro.id) }
                        )
                    )
                }
            }
        }
        CameraButton(
            state = CameraButtonUIState(
                onClick = onCameraClick
            ),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}




fun bookModel2BookCard(
    bm: BookModel,
    onMarkAsReadClick: () -> Unit = {}
): BookCardData {
    return BookCardData(
        id = bm.id,
        titulo = bm.titulo,
        autor = bm.autor,
        imagen = bm.imagen,
        onMarkAsReadClick = onMarkAsReadClick
    )
}

@Preview(showBackground = true)
@Composable
fun BookListScreenPreview() {
    BookyTheme {
        BookListScreen()
    }
}