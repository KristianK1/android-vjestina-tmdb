package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

data class HomeMovieCategoryViewState(
    val movieCategories: List<MovieCategoryLabelViewState> = listOf(),
    val movies: List<HomeMovieViewState> = listOf(),
){
    companion object{
        fun EMPTY(): HomeMovieCategoryViewState{
            return HomeMovieCategoryViewState(
                movieCategories = emptyList(),
                movies = emptyList(),
            )
        }
    }
}

data class HomeMovieViewState(
    val id: Int,
    val imageUrl: String?,
    val isFavorite: Boolean
)
