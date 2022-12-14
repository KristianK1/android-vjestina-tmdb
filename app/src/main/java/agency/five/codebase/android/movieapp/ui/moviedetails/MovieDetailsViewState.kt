package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.ui.component.ActorCardViewState
import agency.five.codebase.android.movieapp.ui.component.CrewmanItemViewState

data class MovieDetailsViewState(
    val id: Int,
    val imageUrl: String?,
    val voteAverage: Float,
    val title: String,
    val overview: String,
    val isFavorite: Boolean,
    val crew: List<CrewmanItemViewState>,
    val cast: List<ActorCardViewState>,
) {
    companion object EMPTY {
        fun getEmptyObject(): MovieDetailsViewState{
            return MovieDetailsViewState(
                id = 0,
                imageUrl = null,
                voteAverage = 0.0f,
                title = "",
                overview = "",
                isFavorite = false,
                crew = listOf(),
                cast = listOf(),
            )
        }
    }
}
