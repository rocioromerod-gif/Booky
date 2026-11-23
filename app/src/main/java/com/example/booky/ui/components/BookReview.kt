package com.example.booky.ui.components
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class BookReviewUiState(
    val reviewText: String,
    val onReviewChanged: (String) -> Unit,
    val placeholder: String = "Este libro..."
)

@Composable
fun BookReview(
    state: BookReviewUiState,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = state.reviewText,
        onValueChange = state.onReviewChanged,
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(16.dp),
        textStyle = LocalTextStyle.current.copy(
            color = Color(0xFF582336)
        ),
        placeholder = {
            Text(
                text = state.placeholder,
                color = Color(0xFF582336)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFD3C2A5),
            unfocusedContainerColor = Color(0xFFD3C2A5),
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary
        )
    )
}
