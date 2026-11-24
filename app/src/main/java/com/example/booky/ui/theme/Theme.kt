package com.example.booky.ui.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

private val BookyColorScheme = darkColorScheme(
    primary = BookyHeader,  // Beige general
    onPrimary = BookyTitleColor ,   // El color del texto del título
    background = BookyBackground,  // El fondo oscuro general
    onBackground = BookyTextColor,  // Las letras generales de texto
    onSurface = BookyTextColor, // El texto dentro de la tarjeta
    secondary = BookyFabBg,   // El color del botón de cámara
    tertiary = BookyStars,   //Color de las estrellas
    surfaceVariant = BookySearchBarBg, //Color de la barra de búsqueda
    onSurfaceVariant = BookySearchText, //Color del texto de la barra de búsqueda
    onTertiary = Color(0xFF89AC76)

)

val BookyTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        textAlign = TextAlign.Center
    ),
    titleLarge = TextStyle(
        fontFamily = BodoniFLF,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    )
)

@Composable
fun BookyTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = BookyColorScheme,
        typography = BookyTypography,
        content = content
    )
}