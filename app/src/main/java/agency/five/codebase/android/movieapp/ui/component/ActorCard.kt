package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.compose.AsyncImage
import coil.request.ImageRequest

data class ActorCardViewState(
    val imageUrl: String?,
    val name: String,
    val character: String,
)


@Composable
fun ActorCard(
    item: ActorCardViewState,
    modifier: Modifier = Modifier,
) {
    Card (
        modifier = Modifier
            .size(width = 125.dp, height = 209.dp)
            .clip(RoundedCornerShape(10.dp)),
        elevation = CardDefaults.cardElevation(2.dp)
    ){
        Column{
            AsyncImage(
                model = item.imageUrl,
                contentDescription = "Image of ${item.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(136.dp)
            )
            Text(
                text = item.name,
                fontSize = 14.sp,
                fontWeight =  FontWeight.Bold,
                color = Color.Black,
                modifier = modifier
                    .padding(start = 7.dp, top = 7.dp)
                    .height(35.dp)
                    .width(87.dp)
            )
            Text(
                text = item.character,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = modifier
                    .width(125.dp)
                    .padding(start = 7.dp, top = 5.dp)
            )

        }
    }
}



@Preview
@Composable
private fun ActorCardPreview(){
    val actor = MoviesMock.getActor();
    val actorPreview = ActorCardViewState(actor.imageUrl, actor.name, actor.character);
    ActorCard(item = actorPreview)
}