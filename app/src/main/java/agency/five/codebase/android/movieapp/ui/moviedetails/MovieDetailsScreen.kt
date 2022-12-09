package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.*
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

private const val USER_CORE_STROKE_WIDTH = 12f

private val movieDetailsMapper: MovieDetailsMapper = MovieDetailsMapperImpl()
val movieDetailViewState = movieDetailsMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

@Composable
fun MovieDetailsRoute(
) {
    val movieDetails by remember { mutableStateOf(movieDetailViewState) }
    MovieDetailsScreen(
        movieDetails,
        onClickLikeButton = { }
    )
}

@Composable
fun MovieDetailsScreen(
    movieDetailsViewState: MovieDetailsViewState,
    onClickLikeButton: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        MovieHeader(
            movieDetailsViewState = movieDetailsViewState,
            onClickLikeButton = onClickLikeButton
        )
        MovieOverview(movieDetailsViewState)
        MovieCrew(movieDetailsViewState)
        MovieCast(movieDetailsViewState)
    }
}

@Composable
fun MovieHeader(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier = Modifier,
    onClickLikeButton: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.movie_details_screen_header_height))
            .background(Color.Cyan)
    ) {
        AsyncImage(
            model = movieDetailsViewState.imageUrl,
            contentDescription = "${movieDetailsViewState.title} - Movie image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(MaterialTheme.spacing.medium)
        ) {
            UserScoreProgressBar(
                percentage = movieDetailsViewState.voteAverage / 10,
                value = movieDetailsViewState.voteAverage,
                color = Color.Green,
                strokeWidth = USER_CORE_STROKE_WIDTH,
                modifier = Modifier.size(dimensionResource(id = R.dimen.movie_details_screen_user_score_size))
            )
            Spacer(
                modifier = Modifier.height(MaterialTheme.spacing.small)
            )
            Text(
                text = movieDetailsViewState.title,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                modifier = Modifier
            )
            Spacer(
                modifier = Modifier.height(MaterialTheme.spacing.medium)
            )
            FavoriteButton(
                onClick = {
                    onClickLikeButton(movieDetailsViewState.id)
                },
                state = movieDetailsViewState.isFavorite,
            )
        }
    }
}

@Composable
fun MovieOverview(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier = Modifier,
) {
    Column {
        Text(
            text = "Overview",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(
                    MaterialTheme.spacing.medium,
                    MaterialTheme.spacing.medium,
                    MaterialTheme.spacing.medium,
                    0.dp
                )
        )
        Text(
            text = movieDetailsViewState.overview,
            modifier = Modifier
                .padding(
                    MaterialTheme.spacing.medium,
                    MaterialTheme.spacing.extraSmall,
                    MaterialTheme.spacing.medium,
                    MaterialTheme.spacing.extraSmall
                )
        )
    }
}

@Composable
fun MovieCrew(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .heightIn(min = 50.dp, max = 400.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(dimensionResource(id = R.dimen.movie_details_screen_crew_grid_cell_min_size)),
            userScrollEnabled = false,
            modifier = Modifier
                .padding(
                    MaterialTheme.spacing.medium,
                    MaterialTheme.spacing.extraSmall,
                    MaterialTheme.spacing.medium,
                    0.dp,
                )
        ) {
            items(
                items = movieDetailsViewState.crew,
            ) { crewman ->

                Box(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, MaterialTheme.spacing.medium)
                ) {
                    CrewmanItem(item = crewman)
                }
            }
        }
    }
}

@Composable
fun MovieCast(
    movieDetailsViewState: MovieDetailsViewState,
    modifier: Modifier = Modifier,
) {
    Column {
        Text(
            text = stringResource(id = R.string.topBilledCast),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(
                    MaterialTheme.spacing.medium,
                    MaterialTheme.spacing.small
                )
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            contentPadding = PaddingValues(MaterialTheme.spacing.medium, 0.dp)
        ) {
            items(
                items = movieDetailsViewState.cast
            ) { actor ->
                Box {
                    ActorCard(
                        item = actor,
                        modifier = Modifier
                            .size(
                                width = dimensionResource(id = R.dimen.movie_details_screen_actor_card_width),
                                height = dimensionResource(id = R.dimen.movie_details_screen_actor_card_height)
                            )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMovieDetailScreen() {
    MovieAppTheme {
        MovieDetailsScreen(
            movieDetailsViewState = movieDetailViewState,
            onClickLikeButton = { }
        )
    }
}
