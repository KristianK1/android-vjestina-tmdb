package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState

data class FavoritesMovieViewState (
    val id: Int,
    val movieCardViewState: MovieCardViewState,
)

data class FavoritesViewState(
   val list: List<FavoritesMovieViewState>
){
    companion object{
        fun EMPTY(): FavoritesViewState{
            return FavoritesViewState(emptyList())
        }
    }
}
