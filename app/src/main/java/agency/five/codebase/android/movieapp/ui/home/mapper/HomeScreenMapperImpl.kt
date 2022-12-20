package agency.five.codebase.android.movieapp.ui.home.mapper

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryTextStringResource
import agency.five.codebase.android.movieapp.ui.home.HomeMovieCategoryViewState
import agency.five.codebase.android.movieapp.ui.home.HomeMovieViewState


class HomeScreenMapperImpl : HomeScreenMapper {
    override fun toHomeMovieCategoryViewState(
        movieCategories: List<MovieCategory>,
        selectedMovieCategory: MovieCategory,
        movies: List<Movie>,
    ): HomeMovieCategoryViewState {

        val movieCategoriesViewStates = movieCategories.map { movieCategory ->
            MovieCategoryLabelViewState(
                itemId = movieCategory.ordinal,
                isSelected = movieCategory == selectedMovieCategory,
                categoryText = MovieCategoryTextStringResource(getMovieCategoryName(movieCategory)),
            )
        }

        val moviesViewState = movies.map { movie ->
            HomeMovieViewState(
                id = movie.id,
                imageUrl = movie.imageUrl,
                isFavorite = movie.isFavorite,
            )
        }

        return HomeMovieCategoryViewState(
            movieCategoriesViewStates,
            moviesViewState
        )
    }

    private fun getMovieCategoryName(category: MovieCategory): Int {
        return when (category) {
            MovieCategory.POPULAR_STREAMING -> R.string.streaming
            MovieCategory.POPULAR_ON_TV -> R.string.on_tv
            MovieCategory.POPULAR_FOR_RENT -> R.string.for_rent
            MovieCategory.POPULAR_IN_THEATRES -> R.string.in_theaters
            MovieCategory.PLAYING_MOVIES -> R.string.playing_movies
            MovieCategory.PLAYING_TV -> R.string.playing_tv
            MovieCategory.UPCOMING_TODAY -> R.string.trending_today
            MovieCategory.UPCOMING_THIS_WEEK -> R.string.trending_this_week
        }
    }
}
