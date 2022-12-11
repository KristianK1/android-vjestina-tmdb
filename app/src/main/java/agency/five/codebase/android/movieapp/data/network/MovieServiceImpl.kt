package agency.five.codebase.android.movieapp.data.network

import agency.five.codebase.android.movieapp.data.network.model.ApiMovieDetails
import agency.five.codebase.android.movieapp.data.network.model.MovieCreditsResponse
import agency.five.codebase.android.movieapp.data.network.model.MovieResponse
import io.ktor.client.*

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
private const val BASE_URL = "https://api.themoviedb.org/3"
private const val API_KEY = "da8a17106e7ca013dd7b54ed7a3a10f2"

class MovieServiceImpl(private val client: HttpClient) : MovieService {
    override suspend fun fetchPopularMovies(): MovieResponse {
// your code goes here
    }
    override suspend fun fetchNowPlayingMovies(): MovieResponse {
// your code goes here
    }
    override suspend fun fetchUpcomingMovies(): MovieResponse {
// your code goes here
    }
    override suspend fun fetchTopRatedMovies(): MovieResponse {
// your code goes here
    }
    override suspend fun fetchMovieDetails(movieId: Int): ApiMovieDetails {
// your code goes here
    }
    override suspend fun fetchMovieCredits(movieId: Int): MovieCreditsResponse {
// your code goes here
    }
}
