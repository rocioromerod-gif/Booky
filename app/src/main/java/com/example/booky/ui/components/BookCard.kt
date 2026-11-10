package com.example.booky.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.booky.R




@Composable
fun BookCard(
    title: String,
    author: String,
    imageRes: Int,
    onMarkAsReadClick: () -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .width(140.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C2523)) // Tono oscuro cálido acorde a Booky
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
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit
                )

                // Botón de opciones
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 8.dp, y = (-8.dp))
                        .size(30.dp)
                        .background(Color.Black.copy(alpha = 0.50f), shape = CircleShape)
                        .clickable { /* Acción del menú */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.options_menu),
                        tint = Color(0xFFE1BC9E) ,
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Menú contextual con la opción Marcar como leído
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
                            onMarkAsReadClick() //Acción que llevará a la pantalla de reseñas
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Título del libro
            Text(
                text = title,
                color = Color(0xFFE1BC9E),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            // Autor del libro
            Text(
                text = author,
                color = Color(0xFFE1BC9E),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = Poppins,
                fontSize = 12.sp
            )
        }
    }
}

//Preview de ejemplo similar a el diseño de canva
@Preview(showBackground = true, backgroundColor = 0xFF1C1816)
@Composable
fun BookCardPreview() {
    BookCard(
        title = "Han cantado Bingo",
        author = "Lana Corujo",
        imageRes = R.drawable.han_cantando_bingo,
        onMarkAsReadClick = {}
    )
}