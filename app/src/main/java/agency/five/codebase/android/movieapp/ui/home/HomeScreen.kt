package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.navigation.MovieDetailsDestination
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()

val popularCategoryViewState =
    homeScreenMapper.toHomeMovieCategoryViewState(movieCategories = listOf(MovieCategory.POPULAR_STREAMING,
        MovieCategory.POPULAR_ON_TV,
        MovieCategory.POPULAR_FOR_RENT,
        MovieCategory.POPULAR_IN_THEATRES),
        selectedMovieCategory = MovieCategory.POPULAR_STREAMING,
        movies = MoviesMock.getMoviesList())

val nowPlayingCategoryViewState =
    homeScreenMapper.toHomeMovieCategoryViewState(movieCategories = listOf(
        MovieCategory.PLAYING_MOVIES,
        MovieCategory.PLAYING_TV,
    ), selectedMovieCategory = MovieCategory.PLAYING_MOVIES, movies = MoviesMock.getMoviesList())

val upcomingCategoryViewState =
    homeScreenMapper.toHomeMovieCategoryViewState(movieCategories = listOf(
        MovieCategory.UPCOMING_TODAY,
        MovieCategory.UPCOMING_THIS_WEEK,
    ), selectedMovieCategory = MovieCategory.UPCOMING_TODAY, movies = MoviesMock.getMoviesList())

@Composable
fun HomeRoute(
    onNavigateToMovieDetails: (String) -> Unit,
) {
    var popularViewState by remember { mutableStateOf(popularCategoryViewState) }
    var playingViewState by remember { mutableStateOf(nowPlayingCategoryViewState) }
    var upcomingViewState by remember { mutableStateOf(upcomingCategoryViewState) }

    HomeScreen(
        popularViewState,
        playingViewState,
        upcomingViewState,
        onCategoryClick = { category ->
            when (category.itemId) {
                in 0..3 -> popularViewState =
                    homeScreenMapper.toHomeMovieCategoryViewState(movieCategories = listOf(
                        MovieCategory.POPULAR_STREAMING,
                        MovieCategory.POPULAR_ON_TV,
                        MovieCategory.POPULAR_FOR_RENT,
                        MovieCategory.POPULAR_IN_THEATRES),
                        selectedMovieCategory = MovieCategory.values()[category.itemId],
                        movies = MoviesMock.getMoviesList()
                    )
                in 4..5 -> playingViewState =
                    homeScreenMapper.toHomeMovieCategoryViewState(movieCategories = listOf(
                        MovieCategory.PLAYING_MOVIES,
                        MovieCategory.PLAYING_TV,
                    ),
                        selectedMovieCategory = MovieCategory.values()[category.itemId],
                        movies = MoviesMock.getMoviesList()
                    )
                else -> upcomingViewState =
                    homeScreenMapper.toHomeMovieCategoryViewState(movieCategories = listOf(
                        MovieCategory.UPCOMING_TODAY,
                        MovieCategory.UPCOMING_THIS_WEEK,
                    ),
                        selectedMovieCategory = MovieCategory.values()[category.itemId],
                        movies = MoviesMock.getMoviesList()
                    )
            }
        },
        onMovieClick = onNavigateToMovieDetails,
    )
}

@Composable
fun HomeScreen(
    popularViewState: HomeMovieCategoryViewState,
    playingViewState: HomeMovieCategoryViewState,
    upcomingViewState: HomeMovieCategoryViewState,
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit,
    onMovieClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        HomeScreenMovieCategory(
            movieCategoryViewState = popularViewState,
            categoryName = stringResource(id = R.string.whatsPopularCategoryTitle),
            onCategoryClick = onCategoryClick,
            onMovieClick = onMovieClick,
        )
        HomeScreenMovieCategory(movieCategoryViewState = playingViewState,
            categoryName = stringResource(id = R.string.freeToWatchTitle),
            onCategoryClick = onCategoryClick,
            onMovieClick = onMovieClick
        )
        HomeScreenMovieCategory(
            movieCategoryViewState = upcomingViewState,
            categoryName = stringResource(id = R.string.trendingTitle),
            onCategoryClick = onCategoryClick,
            onMovieClick = onMovieClick,
        )
    }
}

@Composable
fun HomeScreenMovieCategory(
    movieCategoryViewState: HomeMovieCategoryViewState,
    categoryName: String,
    onCategoryClick: (MovieCategoryLabelViewState) -> Unit,
    onMovieClick: (String) -> Unit,
) {
    Column{
        Text(
            text = categoryName,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            contentPadding = PaddingValues(MaterialTheme.spacing.medium, 0.dp)
        ) {
            items(
                items = movieCategoryViewState.movieCategories
            ) { category ->
                Box {
                    MovieCategoryLabel(
                        item = category,
                        onClick = { onCategoryClick(category) }
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier
                .height(MaterialTheme.spacing.medium)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            contentPadding = PaddingValues(MaterialTheme.spacing.medium, 0.dp)
        ) {
            items(
                items = movieCategoryViewState.movies
            ) { movie ->
                Box {
                    MovieCard(item = MovieCardViewState(
                        imageUrl = movie.imageUrl,
                        isFavorite = movie.isFavorite,
                    ),
                        modifier = Modifier
                            .size(
                                width = dimensionResource(id = R.dimen.home_screen_movie_card_width), 
                                height = dimensionResource(id = R.dimen.home_screen_movie_card_height)
                            ),
                        onClickMovieItem = {
                            onMovieClick(MovieDetailsDestination.createNavigationRoute(movie.id))
                        },
                        onClickLikeButton = { }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    var popularViewState by remember { mutableStateOf(popularCategoryViewState) }
    var playingViewState by remember { mutableStateOf(nowPlayingCategoryViewState) }
    var upcomingViewState by remember { mutableStateOf(upcomingCategoryViewState) }

    HomeScreen(popularViewState = popularViewState,
        playingViewState = playingViewState,
        upcomingViewState = upcomingViewState,
        onCategoryClick = { category ->
            when (category.itemId) {
                in 0..3 ->
                    popularViewState =
                        homeScreenMapper.toHomeMovieCategoryViewState(movieCategories = listOf(
                            MovieCategory.POPULAR_STREAMING,
                            MovieCategory.POPULAR_ON_TV,
                            MovieCategory.POPULAR_FOR_RENT,
                            MovieCategory.POPULAR_IN_THEATRES),
                            selectedMovieCategory = MovieCategory.values()[category.itemId],
                            movies = MoviesMock.getMoviesList()
                        )
                in 4..5 ->
                    playingViewState =
                        homeScreenMapper.toHomeMovieCategoryViewState(movieCategories = listOf(
                            MovieCategory.PLAYING_MOVIES,
                            MovieCategory.PLAYING_TV,
                        ),
                            selectedMovieCategory = MovieCategory.values()[category.itemId],
                            movies = MoviesMock.getMoviesList()
                        )
                in 5..7 ->
                    upcomingViewState =
                        homeScreenMapper.toHomeMovieCategoryViewState(movieCategories = listOf(
                            MovieCategory.UPCOMING_TODAY,
                            MovieCategory.UPCOMING_THIS_WEEK,
                        ),
                            selectedMovieCategory = MovieCategory.values()[category.itemId],
                            movies = MoviesMock.getMoviesList()
                        )
            }
        }, {}
    )
}
