package com.example.booky.ui.components

import androidx.annotation.DrawableRes
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

data class BookCardData(
    val id: Int = 0,
    val titulo: String,
    val autor: String,
    @DrawableRes val imagen: Int,
    val onMarkAsReadClick: () -> Unit = {}
)

@Composable
fun BookCard(
    data: BookCardData,
    modifier: Modifier = Modifier
) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .width(140.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
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
                    painter = painterResource(data.imagen),
                    contentDescription = data.titulo,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit
                )

                // Botón de opciones
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 12.dp, y = (-8.dp))
                        .size(48.dp)
                        .clickable { expanded = true },
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.opciones),
                        tint = MaterialTheme.colorScheme.primary ,
                        modifier = Modifier.size(32.dp)
                    )
                }

                // Menú con la opción Marcar como leído
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = stringResource(R.string.marcar_como_leído))
                        },
                        onClick = {
                            expanded = false
                            data.onMarkAsReadClick() //Acción que llevará a la pantalla de reseñas
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Título del libro
            Text(
                text = data.titulo,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )

            // Autor del libro
            Text(
                text = data.autor,
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
            data = BookCardData(
                id = 1,
                titulo = "Han cantado Bingo",
                autor = "Lana Corujo",
                imagen = R.drawable.han_cantando_bingo,
                onMarkAsReadClick = {}
            )
        )
    }
}