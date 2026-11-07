package ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booky.R

@Composable
fun BookDetailScreen(
    title: String = "Han cantado Bingo",
    author: String = "Lana Corujo",
    coverRes: Int = R.drawable.han_cantando_bingo,
    description: String = "Este libro....",
    rating: Float = 4.5f
) {
    val scrollState = rememberScrollState()

    val backgroundColor = Color(0xFF1E1B18)
    val cardBackgroundColor = Color(0xFFDCC3A4)
    val textColor = Color(0xFFDCC3A4)
    val starColor = Color(0xFFE5C158)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = coverRes),
            contentDescription = "Portada de $title",
            modifier = Modifier
                .width(200.dp)
                .height(300.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )

        Text(
            text = author,
            fontSize = 16.sp,
            color = textColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(cardBackgroundColor)
                .padding(16.dp)
        ) {
            Text(
                text = description,
                color = Color(0xFF2B2118),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            // 4 Estrellas llenas
            repeat(4) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Estrella llena",
                    tint = starColor,
                    modifier = Modifier.size(32.dp).padding(horizontal = 2.dp)
                )
            }
            // 1 Estrella apagada
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Estrella apagada",
                tint = starColor.copy(alpha = 0.3f),
                modifier = Modifier.size(32.dp).padding(horizontal = 2.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun BookDetailScreenPreview() {
    BookDetailScreen()
}