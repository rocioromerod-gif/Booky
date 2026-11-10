package com.example.booky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.res.stringResource
import com.example.booky.ui.theme.BookyTheme
import com.example.booky.ui.pages.BookListScreen // Asegúrate de que apunta a tu pantalla principal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookyTheme {
                BookListScreen(
                    title = stringResource(R.string.pending_readings),
                    books = emptyList(),
                    onBookClick = {},
                    onCameraClick = {}
                )
            }
        }
    }
}