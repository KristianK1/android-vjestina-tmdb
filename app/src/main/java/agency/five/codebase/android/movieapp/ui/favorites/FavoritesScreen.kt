package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.navigation.MovieDetailsDestination
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.res.stringResource
import agency.five.codebase.android.movieapp.R
import androidx.compose.runtime.*
import androidx.compose.ui.res.dimensionResource

@Composable
fun FavoritesRoute(
    onNavigateToMovieDetails: (String) -> Unit,
    viewModel: FavoritesViewModel,
){
    val favoritesViewState: FavoritesViewState by viewModel.favoritesViewState.collectAsState()
    FavoritesScreen(
        favoritesViewState,
        onNavigateToMovieDetails,
        viewModel::removeFavorite,
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
    onClickLikeButton: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = dimensionResource(id = R.dimen.favorites_screen_grid_cell_min_size)),
        modifier = Modifier.padding(MaterialTheme.spacing.small)
    ) {
        header {
            Text(
                text = stringResource(R.string.favorites_screen_header),
                fontWeight = FontWeight.Bold,
                fontSize = 45.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        items(
            items = favorites.list,
            key = { movie ->
                movie.id
            }) { movie ->
            MovieCard(item = movie.movieCardViewState,
                modifier = Modifier
                    .size(
                        width = dimensionResource(id = R.dimen.favorites_screen_movie_card_width),
                        height = dimensionResource(id = R.dimen.favorites_screen_movie_card_height)
                    )
                    .padding(MaterialTheme.spacing.extraSmall, MaterialTheme.spacing.medium),
                onClickMovieItem = {
                    onNavigateToMovieDetails(MovieDetailsDestination.createNavigationRoute(movie.id))
                },
                onClickLikeButton = {
                    onClickLikeButton(movie.id)
                })
        }
    }
}
