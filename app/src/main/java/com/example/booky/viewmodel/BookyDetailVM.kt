package com.example.booky.viewmodel

import androidx.lifecycle.ViewModel
import com.example.booky.seeder.BookModel
import com.example.booky.seeder.libros
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class BookyDetailtUIState(
    val libro: BookModel? = null,
    val textoReview : String = ""
)


class BookyDetailVMl: ViewModel() {

    private val _uiState = MutableStateFlow(BookyDetailtUIState())
    val uiState: StateFlow<BookyDetailtUIState> = _uiState.asStateFlow()

    fun load(bookId: Int) {
        val encontrado = libros.find { it.id == bookId }
        _uiState.update { current ->
            current.copy(libro = encontrado)
        }
    }

    fun onReviewChanged(newText: String) {
        _uiState.update { current ->
            current.copy(textoReview = newText)
        }
    }
}