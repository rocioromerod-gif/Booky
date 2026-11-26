package com.example.booky.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.booky.R
import com.example.booky.ui.components.BookReview
import com.example.booky.ui.components.BookReviewUiState
import com.example.booky.ui.components.BookStarsBar
import com.example.booky.ui.components.RatingBarUiState
import com.example.booky.ui.theme.BookyTheme
import com.example.booky.viewmodel.BookyDetailVMl

@Composable
fun BookDetailScreen(
    id: Int,
    onGoBack: () -> Unit = {},
    vm: BookyDetailVMl = viewModel(),
    //Dentro de cada pagina se debe llamar al vm correspondiente
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val state by vm.uiState.collectAsState()

    LaunchedEffect(Unit) {
        vm.load(id)
    }

    //Si aún no cargo el libro, sale un pequeño texto
    if (state.libro == null) {
        Text(
            text = "Cargando...",
            color = MaterialTheme.colorScheme.onBackground,
            modifier = modifier.padding(16.dp)
        )
        return
    }

    val libro = state.libro!!

    //Toda la pag va dentro de un column
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(24.dp)
    ) {
        Image(
            painter = painterResource(libro.imagen),
            contentDescription = stringResource(R.string.portada),
            modifier = Modifier
                .width(200.dp)
                .height(300.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = libro.titulo,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = libro.autor,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        //Componente bookrv
        BookReview(
            state = BookReviewUiState(
                reviewText = state.textoReview,
                //Esto cambia el texto si se modifica la reseña
                onReviewChanged = vm :: onReviewChanged,
            )
        )

        //Componente de valoracion por estrellas
        BookStarsBar(
            state = RatingBarUiState(
                rating = state.rating,
                onRatingChanged = { newRating ->
                    vm.onRatingChanged(newRating)
                }
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Button(onClick = onGoBack) {
            Text(
                text = stringResource(R.string.volver),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun BookDetailScreenPreview() {
    BookyTheme {
        BookDetailScreen(id = 1, onGoBack = {})
    }
}