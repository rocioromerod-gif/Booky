package com.example.booky.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.booky.R
import com.example.booky.ui.theme.BookyTheme

data class BookCardUiState(
    val id: Int = 0,
    val title: String,
    val author: String,
    val imageRes: Int,
    val onMarkAsReadClick: () -> Unit = {}
)

@Composable
fun BookCard(
    state: BookCardUiState,
    modifier: Modifier = Modifier
) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .width(140.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface) // Tono oscuro cálido acorde a Booky
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
            ) {
                // Portada del libro
                Image(
                    painter = painterResource(id = state.imageRes),
                    contentDescription = state.title,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit
                )

                // Botón de opciones
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 8.dp, y = (-8.dp))
                        .size(40.dp)
                        .clickable { expanded = true },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.options_menu),
                        tint = MaterialTheme.colorScheme.primary ,
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Menú con la opción Marcar como leído
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = stringResource(R.string.mark_as_read))
                        },
                        onClick = {
                            expanded = false
                            state.onMarkAsReadClick() //Acción que llevará a la pantalla de reseñas
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Título del libro
            Text(
                text = state.title,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )

            // Autor del libro
            Text(
                text = state.author,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

//Preview de ejemplo similar a el diseño de canva
@Preview(showBackground = true, backgroundColor = 0xFF1E1B18)
@Composable
fun BookCardPreview() {
    BookyTheme {
        BookCard(
            state = BookCardUiState(
                title = "Han cantado Bingo",
                author = "Lana Corujo",
                imageRes = R.drawable.han_cantando_bingo,
                onMarkAsReadClick = {}
            )
        )
    }
}