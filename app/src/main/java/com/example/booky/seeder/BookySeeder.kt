package com.example.booky.seeder

import androidx.annotation.DrawableRes
import com.example.booky.R

var libros = listOf(

    BookModel(
        1,
        "Han cantado Bingo",
        "Lana Corujo",
        imagen = R.drawable.han_cantando_bingo,
    ),

    BookModel(
        2,
        "El pozo de la ascensión",
        "Brandon Sanderson",
        imagen = R.drawable.pozo_ascension,
    ),

    BookModel(
        3,
        "Corona de medianoche",
        "Sarah J. Maas",
        imagen = R.drawable.tog
    )
)

data class BookModel(
    val id : Int,
    val titulo : String,
    val autor : String,
    @DrawableRes val imagen : Int,
)

enum class GeneroLibro (val etiquetaGenero: String) {
    FANTASIA("Fantasía"),
    FICCION ("Ficción"),
    ROMANCE("Romance"),
    TERROR("Terror"),
    MISTERIO("Misterio")
}

enum class TipoPerfil(val etiquetaPerfil: String) {
    LECTOR("Lector"),
    CLUB("Club")
}