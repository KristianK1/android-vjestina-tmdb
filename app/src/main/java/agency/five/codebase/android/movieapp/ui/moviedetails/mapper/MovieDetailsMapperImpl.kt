package agency.five.codebase.android.movieapp.ui.moviedetails.mapper

import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.component.ActorCardViewState
import agency.five.codebase.android.movieapp.ui.component.CrewmanItemViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

class MovieDetailsMapperImpl : MovieDetailsMapper {
    override fun toMovieDetailsViewState(movieDetails: MovieDetails): MovieDetailsViewState {
        val crew = mutableListOf<CrewmanItemViewState>()
        movieDetails.crew.forEach { crewman ->
            crew.add(
                CrewmanItemViewState(
                    name = crewman.name,
                    job = crewman.job,
                )
            )
        }

        val cast = mutableListOf<ActorCardViewState>()
        movieDetails.cast.forEach { actor ->
            cast.add(
                ActorCardViewState(
                    name = actor.name,
                    character = actor.character,
                    imageUrl = actor.imageUrl,
                )
            )
        }

        val moviedetail = MovieDetailsViewState(
            id = movieDetails.movie.id,
            imageUrl = movieDetails.movie.imageUrl,
            voteAverage = movieDetails.voteAverage,
            title = movieDetails.movie.title,
            overview = movieDetails.movie.overview,
            isFavorite = movieDetails.movie.isFavorite,
            crew = crew,
            cast = cast,
        )
        return moviedetail
    }
}