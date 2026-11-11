package com.example.booky.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.booky.R

data class RatingBarUiState(
    val rating: Int,
    val onRatingChanged: (Int) -> Unit
)

@Composable
fun BookStarsBar(
    state: RatingBarUiState,
    modifier: Modifier = Modifier,
    maxRating: Int = 5
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..maxRating) {
            val isSelected = i <= state.rating
            IconButton(
                onClick = { state.onRatingChanged(i) }
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = if (isSelected) {
                        stringResource(R.string.cd_filled_star)
                    } else {
                        stringResource(R.string.cd_empty_star)
                    },

                    tint = if (isSelected) {
                        MaterialTheme.colorScheme.tertiary
                    } else {
                        MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f)
                    },
                    modifier = Modifier
                        .size(32.dp)
                        .padding(horizontal = 2.dp)
                )
            }
        }
    }
}
