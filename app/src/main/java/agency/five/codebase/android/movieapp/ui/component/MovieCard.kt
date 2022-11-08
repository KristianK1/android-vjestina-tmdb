package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.ui.theme.Spacing
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.Shapes
import androidx.compose.ui.res.dimensionResource

data class MovieCardViewState(
    val title: String,
    val imageUrl: String?,
);

@Composable
fun MovieCard(
    item: MovieCardViewState,
    modifier: Modifier,
    isFavorite: MutableState<Boolean>,
    onClickMovieItem: () -> Unit,
    onClickLikeButton: () -> Unit
) {
    val localSpacing = Spacing()
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
                    .padding(localSpacing.small)
                    .size(dimensionResource(id = R.dimen.favoriteBt_size_in_movieCard)),
                state = isFavorite
            )
        }
    }
}

@Preview
@Composable
fun previewMovieCard() {
    val movie = MoviesMock.getMoviesList()[0];
    val state = remember {
        mutableStateOf(false)
    }
    val moviePreview = MovieCardViewState(
        movie.title, movie.imageUrl
    )

    MovieCard(item = moviePreview,
        modifier = Modifier.size(200.dp, 350.dp),
        isFavorite = state,
        onClickMovieItem = {
            Log.i("clicked", "clicked movie card")
        },
        onClickLikeButton = {
            Log.i("clicked", "like button")
            state.value = !state.value
        })
}