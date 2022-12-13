package agency.five.codebase.android.movieapp.data.repository

import agency.five.codebase.android.movieapp.data.database.DbFavoriteMovie
import agency.five.codebase.android.movieapp.data.database.FavoriteMovieDao
import agency.five.codebase.android.movieapp.data.network.MovieService
import agency.five.codebase.android.movieapp.data.network.model.MovieResponse
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.model.MovieDetails
import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

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
        Log.i("kkDebug", "new favorites list from db")
        for (movie in dbFavoriteMovies) {
            Log.i("kkDebug", "fav${movie.id}")
        }
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

    override fun movieDetails(movieId: Int): Flow<MovieDetails> = flow {
        emit(movieService.fetchMovieDetails(movieId) to movieService.fetchMovieCredits(movieId))
    }.flatMapLatest { (apiMovieDetails, apiMovieCredits) ->
        movieDao.favorites()
            .map { favoriteMovies ->
                apiMovieDetails.toMovieDetails(
                    isFavorite = favoriteMovies.any { it.id == apiMovieDetails.id },
                    crew = apiMovieCredits.crew,
                    cast = apiMovieCredits.cast,
                )
            }
    }.flowOn(bgDispatcher)

    override fun favoriteMovies(): Flow<List<Movie>> = favorites


    private suspend fun findMovie(movieId: Int): Movie? {
        for (movieCategory in MovieCategory.values()) {
            val movies = movies(movieCategory).first()
            for (movie in movies) {
                if (movie.id == movieId) {
                    return movie
                }
            }
        }
        return null
    }

    override suspend fun addMovieToFavorites(movieId: Int, posterUrl: String) {
        Log.i("kkDebug", "$movieId|$posterUrl")
        val dbFavoriteMovie = DbFavoriteMovie(id = movieId, posterUrl = posterUrl)
        movieDao.addFavorites(dbFavoriteMovie)
    }

    override suspend fun removeMovieFromFavorites(movieId: Int) {
        movieDao.removeFavorites(movieId)
    }

    override suspend fun toggleFavorite(movieId: Int) {
        runBlocking {
            val movie = findMovie(movieId)
            Log.i("kkDebug", "wer WEER")
            if (movie != null) {
                Log.i("kkDebug", "wer WEER2")
//            val listOfFavorites = mov
//            Log.i("kkDebug", "wer WEER3")
//            val isFavorite = listOfFavorites.any { it.id == movieId }
//            Log.i("kkDebug", "wer $isFavorite")
                val isFavorite = false
                if (isFavorite) removeMovieFromFavorites(movieId)
                else addMovieToFavorites(movieId, movie.imageUrl!!)
            }
        }
    }

}
