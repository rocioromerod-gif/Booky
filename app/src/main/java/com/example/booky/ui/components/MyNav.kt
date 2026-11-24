package com.example.booky.ui.components

import com.example.booky.ui.pages.BookDetailScreen
import com.example.booky.ui.pages.BookListScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.booky.ui.pages.BookFormScreen
import com.example.booky.viewmodel.BookyDetailVMl
import kotlinx.serialization.Serializable

@Serializable object RutaBookList
@Serializable data class RutaBookDetail(val identificador: Int)
@Serializable object RutaBookForm

@Composable
fun MyNav(nav: NavHostController){

    NavHost(
        navController = nav,
        startDestination = RutaBookForm
    ){
        composable<RutaBookList>{
            BookListScreen (
                onBookClick = {nav.navigate(RutaBookDetail(id))})
        }

        composable<RutaBookForm> {
            BookFormScreen(
                onSaved = { nav.navigate(RutaBookList) }
            )
        }

        composable<RutaBookDetail> { backStackEntry ->
            val id = backStackEntry.toRoute<RutaBookDetail>().identificador
            val detailVm: BookyDetailVMl = viewModel()

            BookDetailScreen(
                id = id,
                onGoBack = { nav.popBackStack() },
                vm = detailVm
            )
        }
     }
}
