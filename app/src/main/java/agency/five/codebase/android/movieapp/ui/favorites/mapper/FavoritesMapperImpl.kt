package agency.five.codebase.android.movieapp.ui.favorites.mapper

import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesMovieViewState
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewState

class FavoritesMapperImpl: FavoritesMapper {
    override fun toFavoritesViewState(favoriteMovies: List<Movie>): FavoritesViewState {
        val list: MutableList<FavoritesMovieViewState> = mutableListOf()
        for(movie in favoriteMovies){
            list.add(FavoritesMovieViewState(movie.id, MovieCardViewState(movie.imageUrl, movie.isFavorite)))
        }
        return FavoritesViewState(list)
    }
}