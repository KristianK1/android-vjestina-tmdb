package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.Movie
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MovieCard(
    item: Movie,
    modifier: Modifier = Modifier,
    onClickMovieItem: () -> Unit = {

    },
) {
    Card(
        modifier = modifier
            .size(width = 105.dp, height = 154.dp)
            .clip(RoundedCornerShape(10.dp)),
    ) {
        Box {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .clickable { onClickMovieItem() }
            )
            FavoriteButton(
                modifier = modifier
                    .padding(10.dp),
                checked = false
            )
        }
    }
}


@Preview
@Composable
fun previewMovieCard(){
    val movie = MoviesMock.getMoviesList()[0];
    MovieCard(item = movie)
}