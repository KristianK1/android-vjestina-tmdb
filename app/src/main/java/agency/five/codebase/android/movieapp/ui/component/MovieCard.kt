package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.Shapes
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*

data class MovieCardViewState(
    val title: String,
    val imageUrl: String?,
    val isFavorite: Boolean,
    );

@Composable
fun MovieCard(
    item: MovieCardViewState,
    modifier: Modifier,
    onClickMovieItem: () -> Unit,
    onClickLikeButton: () -> Unit
) {
    Card(
        modifier = modifier
            .clip(Shapes.large)
            .clickable { onClickMovieItem.invoke() },
    ) {
        Box {
            AsyncImage(
                model = item.imageUrl,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            FavoriteButton(
                onClick = onClickLikeButton,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small),
                state = item.isFavorite
            )
        }
    }
}

@Preview
@Composable
fun PreviewMovieCard() {
    val movie = MoviesMock.getMoviesList()[0];

    var movieState by remember {
      mutableStateOf(
          MovieCardViewState(
              movie.title, movie.imageUrl, false
          )
      )
    }

    MovieCard(item = movieState,
        modifier = Modifier.size(100.dp, 170.dp),
        onClickMovieItem = {
        },
        onClickLikeButton = {
            movieState = movieState.copy(isFavorite = !movieState.isFavorite)
        })
}
