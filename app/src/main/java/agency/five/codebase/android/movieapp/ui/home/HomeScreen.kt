package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.navigation.MovieDetailsDestination
import agency.five.codebase.android.movieapp.ui.component.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeRoute(
    onNavigateToMovieDetails: (String) -> Unit,
    viewModel: HomeViewModel,
) {
    val popularViewState: HomeMovieCategoryViewState by viewModel.popularViewState.collectAsState()
    val playingViewState: HomeMovieCategoryViewState by viewModel.nowPlayingViewState.collectAsState()
    val upcomingViewState: HomeMovieCategoryViewState by viewModel.upcomingViewState.collectAsState()


    HomeScreen(
        popularViewState,
        playingViewState,
        upcomingViewState,
        viewModel::switchCategories,
        onMovieClick = onNavigateToMovieDetails,
        onLikeClick = viewModel::toggleFavorite
    )
}

@Composable
fun HomeScreen(
    popularViewState: HomeMovieCategoryViewState,
    playingViewState: HomeMovieCategoryViewState,
    upcomingViewState: HomeMovieCategoryViewState,
    onCategoryClick: (Int) -> Unit,
    onMovieClick: (String) -> Unit,
    onLikeClick: (Int) -> Unit,
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
            onLikeClick = onLikeClick,
        )
        HomeScreenMovieCategory(movieCategoryViewState = playingViewState,
            categoryName = stringResource(id = R.string.freeToWatchTitle),
            onCategoryClick = onCategoryClick,
            onMovieClick = onMovieClick,
            onLikeClick = onLikeClick,
        )
        HomeScreenMovieCategory(
            movieCategoryViewState = upcomingViewState,
            categoryName = stringResource(id = R.string.trendingTitle),
            onCategoryClick = onCategoryClick,
            onMovieClick = onMovieClick,
            onLikeClick = onLikeClick,
        )
    }
}

@Composable
fun HomeScreenMovieCategory(
    movieCategoryViewState: HomeMovieCategoryViewState,
    categoryName: String,
    onCategoryClick: (Int) -> Unit,
    onMovieClick: (String) -> Unit,
    onLikeClick: (Int) -> Unit,
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
                        onClick = { onCategoryClick(category.itemId) }
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
                        onClickLikeButton = { onLikeClick(movie.id) }
                    )
                }
            }
        }
    }
}
