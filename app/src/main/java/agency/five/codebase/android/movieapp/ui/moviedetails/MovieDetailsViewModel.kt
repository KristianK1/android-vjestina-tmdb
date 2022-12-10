package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private var movieId: Int,
    private val movieRepository: MovieRepository,
    private val movieDetailsScreenMapper: MovieDetailsMapper,
) : ViewModel() {
    private val movieDetailsViewStateInternal: MutableStateFlow<MovieDetailsViewState> =
        MutableStateFlow(
            MovieDetailsViewState(
                id = 0,
                imageUrl = null,
                voteAverage = 0.0f,
                title = "",
                overview = "",
                isFavorite = false,
                crew = listOf(),
                cast = listOf(),
            )
        )
    val movieDetailViewState: StateFlow<MovieDetailsViewState> =
        movieDetailsViewStateInternal.asStateFlow()

    init {
        getMovieDetails(movieId)
    }

    fun getMovieDetails(id: Int) {
        movieId = id
        viewModelScope.launch {
            movieRepository.movieDetails(movieId = id).collect { movieDetails ->
                movieDetailsViewStateInternal.value =
                    movieDetailsScreenMapper.toMovieDetailsViewState(movieDetails)
            }
        }
    }

    fun toggleFavorite(id: Int) {
        viewModelScope.launch {
            movieRepository.toggleFavorite(id)
        }
    }
}
