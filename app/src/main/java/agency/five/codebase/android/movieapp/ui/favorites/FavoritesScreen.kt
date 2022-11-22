package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.navigation.MovieDetailsDestination
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme


private val favoritesMapper: FavoritesMapper = FavoritesMapperImpl()

// multiple view states if required
val favoritesViewState = favoritesMapper.toFavoritesViewState(MoviesMock.getMoviesList())

@Composable
fun FavoritesRoute(
    onNavigateToMovieDetails: (String) -> Unit,
) {
    val favorites by remember { mutableStateOf(favoritesViewState) }
    FavoritesScreen(
        favorites,
        onNavigateToMovieDetails
    )
}

fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit,
) {
    item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}

@Composable
fun FavoritesScreen(
    favorites: FavoritesViewState,
    onNavigateToMovieDetails: (String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(130.dp),
        modifier = Modifier.padding(MaterialTheme.spacing.small)
    ) {
        header {
            Text(
                text = "Favorites",
                fontWeight = FontWeight.Bold,
                fontSize = 45.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        items(items = favorites.list, key = { movie ->
            movie.id
        }) { movie ->
            MovieCard(item = movie.movieCardViewState,
                modifier = Modifier
                    .size(100.dp, 220.dp)
                    .padding(MaterialTheme.spacing.extraSmall, MaterialTheme.spacing.medium),
                onClickMovieItem = {
                    onNavigateToMovieDetails(MovieDetailsDestination.createNavigationRoute(movie.id))
                },
                onClickLikeButton = {
                })
        }
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    MovieAppTheme {
        FavoritesScreen(
            favoritesViewState,
            onNavigateToMovieDetails = { }
        )
    }
}