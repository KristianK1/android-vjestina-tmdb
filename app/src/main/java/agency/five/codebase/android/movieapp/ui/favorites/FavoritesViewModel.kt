package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val movieRepository: MovieRepository,
    favoritesScreenMapper: FavoritesMapper,
// other parameters if needed
) : ViewModel() {
    private val favoritesViewStateInternal: MutableStateFlow<FavoritesViewState> = MutableStateFlow(
        FavoritesViewState(emptyList())
    )
    val favoritesViewState: StateFlow<FavoritesViewState> = favoritesViewStateInternal.asStateFlow()

    init {
        viewModelScope.launch{
            movieRepository.favoriteMovies().collect{ movies ->
                favoritesViewStateInternal.value = favoritesScreenMapper.toFavoritesViewState(movies)
            }
        }
    }

    fun someAction() {
    }
}