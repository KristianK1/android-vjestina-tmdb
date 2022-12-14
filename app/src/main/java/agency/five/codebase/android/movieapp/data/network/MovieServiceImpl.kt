package agency.five.codebase.android.movieapp.data.network

import agency.five.codebase.android.movieapp.data.network.model.MovieCreditsResponse
import agency.five.codebase.android.movieapp.data.network.model.MovieDetailsResponse
import agency.five.codebase.android.movieapp.data.network.model.MovieResponse
import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
private const val BASE_URL = "https://api.themoviedb.org/3"
private const val API_KEY = "79470ca26f92491f06685555d52eb3ac"

class MovieServiceImpl(private val client: HttpClient) : MovieService {
    override suspend fun fetchPopularMovies(): MovieResponse {
        val httpResponse = client.get("$BASE_URL/movie/popular?api_key=$API_KEY")
        return httpResponse.body<MovieResponse>()
    }

    override suspend fun fetchNowPlayingMovies(): MovieResponse {
        val httpResponse = client.get("$BASE_URL/movie/now_playing?api_key=$API_KEY")
        return httpResponse.body<MovieResponse>()
    }

    override suspend fun fetchUpcomingMovies(): MovieResponse {
        val httpResponse = client.get("$BASE_URL/movie/upcoming?api_key=$API_KEY")
        return httpResponse.body<MovieResponse>()
    }

    override suspend fun fetchTopRatedMovies(): MovieResponse {
        val httpResponse = client.get("$BASE_URL/movie/top_rated?api_key=$API_KEY")
        return httpResponse.body<MovieResponse>()
    }

    override suspend fun fetchMovieDetails(movieId: Int): MovieDetailsResponse {
        val httpResponse = client.get("$BASE_URL/movie/$movieId?api_key=$API_KEY")
        return httpResponse.body<MovieDetailsResponse>()
    }

    override suspend fun fetchMovieCredits(movieId: Int): MovieCreditsResponse {
        val httpResponse = client.get("$BASE_URL/movie/$movieId/credits?api_key=$API_KEY")
        return httpResponse.body<MovieCreditsResponse>()
    }
}
