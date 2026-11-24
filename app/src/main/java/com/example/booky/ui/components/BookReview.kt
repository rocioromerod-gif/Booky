package com.example.booky.ui.components
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.booky.ui.theme.BookyTextColor
import com.example.booky.ui.theme.BookyTitleColor

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
            color = MaterialTheme.colorScheme.primary
        ),
        placeholder = {
            Text(
                text = state.placeholder,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.onPrimary,
        )
    )
}
