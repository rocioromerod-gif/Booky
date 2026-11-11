package com.example.booky.ui.pages

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
import com.example.booky.ui.components.BookReview
import com.example.booky.ui.components.BookReviewUiState
import com.example.booky.ui.components.BookStarsBar

@Composable
fun BookDetailScreen(
    title: String = stringResource(R.string.default_book_title),
    author: String = stringResource(R.string.default_book_author),
    coverRes: Int = R.drawable.han_cantando_bingo,
    description: String = stringResource(R.string.default_book_description),
    rating: Float = 4.5f,
    reviewText: String,
    onReviewChanged: (String) -> Unit
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
            painter = painterResource(id = coverRes),
            contentDescription = stringResource(R.string.cd_book_cover, title),
            modifier = Modifier
                .width(200.dp)
                .height(300.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = author,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
        )

        // Componente de reseña
        BookReview(
            state = BookReviewUiState(
                reviewText = reviewText,
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
        // En la preview declaramos un estado temporal para que se pueda probar visualmente
        var previewReview by remember { mutableStateOf("") }

        BookDetailScreen(
            reviewText = previewReview,
            onReviewChanged = { previewReview = it }
        )
    }
}