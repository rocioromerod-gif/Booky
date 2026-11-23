package com.example.booky.viewmodel


import androidx.lifecycle.ViewModel
import com.example.booky.seeder.BookModel
import com.example.booky.seeder.libros
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class BookyListUIState(
    val libros: List<BookModel> = emptyList()
)

class BookyListVMl: ViewModel() {

    private val _uiState = MutableStateFlow(BookyListUIState())
    val uiState: StateFlow<BookyListUIState> = _uiState.asStateFlow()


    fun load(){
        _uiState.value = BookyListUIState(
            libros = libros
        )
    }
}
