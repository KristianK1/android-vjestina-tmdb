package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val movieRepository: MovieRepository,
    favoritesScreenMapper: FavoritesMapper,
) : ViewModel() {

    val favoritesViewState: StateFlow<FavoritesViewState> =
        movieRepository.favoriteMovies().map { movies ->
            favoritesScreenMapper.toFavoritesViewState(movies)
        }.stateIn(viewModelScope, SharingStarted.Eagerly, FavoritesViewState(emptyList()))

    fun toggleFavorite(id: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(id)
        }
    }
}
