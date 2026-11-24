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
    val textoReview : String = "",
    val libros : List<BookModel> = emptyList(),
    val rating :Int = 0
)


class BookyDetailVMl: ViewModel() {

    private val _uiState = MutableStateFlow(BookyDetailtUIState())
    val uiState: StateFlow<BookyDetailtUIState> = _uiState.asStateFlow()

    fun load(id: Int) {
        val libroResena = libros.find { it.id == id }
        _uiState.update { current ->
            current.copy(
                libro = libroResena,
                textoReview = current.textoReview,
                rating = current.rating
            )
        }
    }


    fun onReviewChanged(newText: String) {
        _uiState.update { current ->
            current.copy(textoReview = newText)
        }
    }

    fun onRatingChanged(newRating: Int) {
        _uiState.update { current ->
            current.copy(rating = newRating)
        }
    }
}
