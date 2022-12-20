package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.data.network.BASE_IMAGE_URL
import agency.five.codebase.android.movieapp.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("results")
    val movies: List<ApiMovieDetailsMultiple>,
)

@Serializable
data class ApiMovieDetailsMultiple(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("vote_average")
    val voteAverage: Float, //was double
    @SerialName("release_date")
    val releaseDate: String? = null,
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
}
