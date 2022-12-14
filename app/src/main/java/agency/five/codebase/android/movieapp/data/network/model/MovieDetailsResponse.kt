package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.data.network.BASE_IMAGE_URL
import agency.five.codebase.android.movieapp.model.Actor
import agency.five.codebase.android.movieapp.model.Crewman
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieDetailsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("genres")
    val genres: List<ApiGenre>,
    @SerialName("vote_average")
    val voteAverage: Float,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("runtime")
    val runtime: Int,
    @SerialName("spoken_languages")
    val languages: List<ApiLanguage>,
) {
    fun toMovie(isFavorite: Boolean): Movie {
        return Movie(
            id = id,
            title = title,
            overview = overview,
            imageUrl = "$BASE_IMAGE_URL$posterPath",
            isFavorite = isFavorite
        )
    }

    fun toMovieDetails(
        isFavorite: Boolean,
        crew: List<ApiCrewman>,
        cast: List<ApiActor>,
    ): MovieDetails {
        return MovieDetails(
            movie = toMovie(isFavorite),
            voteAverage = voteAverage,
            releaseDate = releaseDate,
            language = languages[0].name,
            runtime = runtime,
            crew = crew.map { apiCrewman ->
                apiCrewman.toCrewman()
            },
            cast = cast.map { apiActor ->
                apiActor.toActor()
            }
        )
    }
}

@Serializable
data class ApiLanguage(
    @SerialName("english_name")
    val name: String,
)

@Serializable
data class ApiGenre(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
)
