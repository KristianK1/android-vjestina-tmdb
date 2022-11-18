package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.theme.Shapes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage

data class ActorCardViewState(
    val imageUrl: String?,
    val name: String,
    val character: String,
)

@Composable
fun ActorCard(
    item: ActorCardViewState,
    modifier: Modifier,
) {
    Card(
        modifier = modifier
            .clip(Shapes.large),
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.actorCard_Elevation)),
    ) {
        Column {
            AsyncImage(
                model = item.imageUrl,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Image of ${item.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, true)
            )
            Text(
                text = item.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.small,
                        end = MaterialTheme.spacing.small,
                        top = MaterialTheme.spacing.small,
                        bottom = MaterialTheme.spacing.default
                    )
            )
            Text(
                text = item.character,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
        }
    }
}


@Preview
@Composable
private fun ActorCardPreview() {
    val actor = MoviesMock.getActor();
    val actorPreview = ActorCardViewState(
        actor.imageUrl, actor.name, actor.character,
    );
    ActorCard(item = actorPreview, Modifier.size(width = 300.dp, height = 580.dp))
}
