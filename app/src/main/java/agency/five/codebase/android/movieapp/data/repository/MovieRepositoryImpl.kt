package agency.five.codebase.android.movieapp.data.repository

import agency.five.codebase.android.movieapp.data.database.FavoriteMovieDao
import agency.five.codebase.android.movieapp.data.network.MovieService
import agency.five.codebase.android.movieapp.data.network.model.MovieResponse
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.model.MovieDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val movieDao: FavoriteMovieDao,
    private val bgDispatcher: CoroutineDispatcher,
) : MovieRepository {

    private val moviesByCategory: Map<MovieCategory, Flow<List<Movie>>> = MovieCategory.values()
        .associateWith { movieCategory ->
            flow {
                val movieResponse: MovieResponse? = when (movieCategory) {
                    MovieCategory.POPULAR_STREAMING -> {
                        movieService.fetchPopularMovies()
                    }
                    MovieCategory.POPULAR_ON_TV -> {
                        movieService.fetchUpcomingMovies()
                    }
                    MovieCategory.POPULAR_FOR_RENT -> {
                        movieService.fetchTopRatedMovies()
                    }
                    MovieCategory.POPULAR_IN_THEATRES -> {
                        movieService.fetchNowPlayingMovies()
                    }
                    MovieCategory.PLAYING_MOVIES -> {
                        movieService.fetchNowPlayingMovies()
                    }
                    MovieCategory.PLAYING_TV -> {
                        movieService.fetchTopRatedMovies()
                    }
                    MovieCategory.UPCOMING_TODAY -> {
                        movieService.fetchNowPlayingMovies()
                    }
                    MovieCategory.UPCOMING_THIS_WEEK -> {
                        movieService.fetchTopRatedMovies()
                    }
                }
                emit(movieResponse!!.movies) //TODO remove !!

            }.flatMapLatest { apiMovies ->
                movieDao.favorites().map { favoriteMovies ->
                    apiMovies.map { apiMovie ->
                        apiMovie.toMovie(isFavorite = favoriteMovies.any { it.id == apiMovie.id })
                    }
                }
            }.shareIn(
                scope = CoroutineScope(bgDispatcher),
                started = SharingStarted.WhileSubscribed(1000L),
                replay = 1,
            )
        }

    private val favorites = movieDao.favorites().map { dbFavoriteMovies ->
        dbFavoriteMovies.map { dbFavoriteMovie ->
            Movie(
                id = dbFavoriteMovie.id,
                imageUrl = dbFavoriteMovie.posterUrl,
                title = "",
                overview = "",
                isFavorite = true,
            )
        }
    }

    override fun movies(movieCategory: MovieCategory): Flow<List<Movie>> =
        moviesByCategory[movieCategory]!!

    override fun movieDetails(movieId: Int): Flow<MovieDetails> {
        TODO("Not yet implemented")
    }

    override fun favoriteMovies(): Flow<List<Movie>> = favorites

//    private suspend fun findMovie(movieId: Int): Movie {
//
//    }

    override suspend fun addMovieToFavorites(movieId: Int) {

    //        movieDao.addFavorites()
    }

    override suspend fun removeMovieFromFavorites(movieId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun toggleFavorite(movieId: Int) {
        TODO("Not yet implemented")
    }

}
