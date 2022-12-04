package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository,
    homeScreenMapper: HomeScreenMapper,
) : ViewModel() {
    private val popularCategories = listOf(
        MovieCategory.POPULAR_STREAMING,
        MovieCategory.POPULAR_ON_TV,
        MovieCategory.POPULAR_FOR_RENT,
        MovieCategory.POPULAR_IN_THEATRES,
    )

    private val nowPlayingCategories = listOf(
        MovieCategory.PLAYING_MOVIES,
        MovieCategory.PLAYING_TV,
    )

    private val upcomingCategories = listOf(
        MovieCategory.UPCOMING_TODAY,
        MovieCategory.UPCOMING_THIS_WEEK,
    )

    private val popularCategorySelected = MutableStateFlow<MovieCategory>(popularCategories[0])
    private val nowPlayingCategorySelected = MutableStateFlow<MovieCategory>(nowPlayingCategories[0])
    private val upcomingCategorySelected = MutableStateFlow<MovieCategory>(upcomingCategories[0])


    private val popularViewStateInternal: MutableStateFlow<HomeMovieCategoryViewState> =
        MutableStateFlow(HomeMovieCategoryViewState())
    val popularViewState = popularViewStateInternal.asStateFlow()

    private val nowPlayingViewStateInternal: MutableStateFlow<HomeMovieCategoryViewState> =
        MutableStateFlow(HomeMovieCategoryViewState())
    val nowPlayingViewState = nowPlayingViewStateInternal.asStateFlow()

    private val upcomingViewStateInternal: MutableStateFlow<HomeMovieCategoryViewState> =
        MutableStateFlow(
            HomeMovieCategoryViewState()
        )
    val upcomingViewState = upcomingViewStateInternal.asStateFlow()


    init {
        viewModelScope.launch {
            movieRepository.popularMovies(popularCategorySelected.value).collect { movies ->
                popularViewStateInternal.value = homeScreenMapper.toHomeMovieCategoryViewState(
                    movieCategories = popularCategories,
                    movies = movies,
                    selectedMovieCategory = popularCategorySelected.value
                )
            }
        }

        viewModelScope.launch {
            movieRepository.nowPlayingMovies(nowPlayingCategorySelected.value).collect { movies ->
                nowPlayingViewStateInternal.value = homeScreenMapper.toHomeMovieCategoryViewState(
                    movieCategories = nowPlayingCategories,
                    movies = movies,
                    selectedMovieCategory = nowPlayingCategorySelected.value
                )
            }
        }

        viewModelScope.launch {
            movieRepository.upcomingMovies(upcomingCategorySelected.value).collect { movies ->
                upcomingViewStateInternal.value = homeScreenMapper.toHomeMovieCategoryViewState(
                    movieCategories = upcomingCategories,
                    movies = movies,
                    selectedMovieCategory = upcomingCategorySelected.value
                )
            }
        }
    }

    fun switchCategories(id: Int){
        when (id) {
            MovieCategory.POPULAR_STREAMING.ordinal,
            MovieCategory.POPULAR_ON_TV.ordinal,
            MovieCategory.POPULAR_FOR_RENT.ordinal,
            MovieCategory.POPULAR_IN_THEATRES.ordinal -> {
                popularCategorySelected.value = MovieCategory.values()[id]
            }

            MovieCategory.PLAYING_TV.ordinal,
            MovieCategory.PLAYING_MOVIES.ordinal -> {
                nowPlayingCategorySelected.value = MovieCategory.values()[id]
            }
            MovieCategory.UPCOMING_TODAY.ordinal,
            MovieCategory.UPCOMING_THIS_WEEK.ordinal -> {
                upcomingCategorySelected.value = MovieCategory.values()[id]
            }
        }
    }

    fun toggleFavorite(id: Int){
        viewModelScope.launch {
            movieRepository.toggleFavorite(id)
        }
    }
}