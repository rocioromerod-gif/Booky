package com.example.booky.ui.pages

import android.R.attr.rating
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booky.R
import com.example.booky.ui.components.RatingBarUiState
import com.example.booky.ui.theme.BookyTheme
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booky.ui.components.BookReview
import com.example.booky.ui.components.BookReviewUiState
import com.example.booky.ui.components.BookStarsBar
import com.example.booky.viewmodel.BookyDetailVMl

data class BookyDetailData(
    val id: Int,
    @DrawableRes val imagen: Int,
    val titulo: String,
    val autor: String,
    val reseña: String = "",
    val estrellas : Int = 0
)
@Composable
fun BookDetailScreen(
    data: BookyDetailData,
  modifier : Modifier = Modifier,
  vm: BookyDetailVMl = viewModel(),
  onReviewChanged: (String) -> Unit = {},
  onRatingChanged: (Int) -> Unit = {},
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(data.imagen),
            contentDescription = stringResource(R.string.portada),
            modifier = Modifier
                .width(200.dp)
                .height(300.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = data.titulo,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = data.autor,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
        )

        // Componente de reseña

        BookReview(
            state = BookReviewUiState(
                reviewText = data.reseña,
                onReviewChanged = onReviewChanged,
                placeholder = "Este libro..."
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Componente de las estrellas
        var currentBookRating by remember { mutableStateOf(rating.toInt()) }

        BookStarsBar(
            state = RatingBarUiState(
                rating = currentBookRating.toInt(),
                onRatingChanged = { newRating ->
                    currentBookRating = newRating.toInt()
                }
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun BookDetailScreenPreview() {
    BookyTheme {
        var previewReview by remember { mutableStateOf("") }
        var previewRating by remember { mutableStateOf(4) }

        BookDetailScreen(
            data = BookyDetailData(
                id = 1,
                imagen = R.drawable.han_cantando_bingo,
                titulo = "Han cantado Bingo",
                autor = "Lana Corujo",
                reseña = previewReview,
                estrellas = previewRating
            ),
            onReviewChanged = { previewReview = it },
            onRatingChanged = { previewRating = it }
        )
    }
}