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
        Log.i("kkDebug", "FIVE")
        Log.i("kkDebug", "$BASE_URL/movie/popular?api_key=$API_KEY")
        val httpResponse = client.get("$BASE_URL/movie/popular?api_key=$API_KEY")
//        if (httpResponse.status.value in 200..299) {
            return httpResponse.body<MovieResponse>()
//        }
    }
    override suspend fun fetchNowPlayingMovies(): MovieResponse {
        Log.i("kkDebug", "FOUR")
        Log.i("kkDebug", "$BASE_URL/movie/now_playing?api_key=$API_KEY")
        val httpResponse = client.get("$BASE_URL/movie/now_playing?api_key=$API_KEY")
//        if (httpResponse.status.value in 200..299) {
            return httpResponse.body<MovieResponse>()
//        }
    }
    override suspend fun fetchUpcomingMovies(): MovieResponse {
        Log.i("kkDebug", "THREE")
        Log.i("kkDebug", "$BASE_URL/movie/upcoming?api_key=$API_KEY")
        val httpResponse = client.get("$BASE_URL/movie/upcoming?api_key=$API_KEY")
//        if (httpResponse.status.value in 200..299) {
            return httpResponse.body<MovieResponse>()
//        }
    }
    override suspend fun fetchTopRatedMovies(): MovieResponse {
        Log.i("kkDebug", "TWO")
        Log.i("kkDebug", "$BASE_URL/movie/top_rated?api_key=$API_KEY")
        val httpResponse = client.get("$BASE_URL/movie/top_rated?api_key=$API_KEY")
//        if (httpResponse.status.value in 200..299) {
            return httpResponse.body<MovieResponse>()
//        }
    }
    override suspend fun fetchMovieDetails(movieId: Int): MovieDetailsResponse {
        Log.i("kkDebug", "one$movieId")
        Log.i("kkDebug", "$BASE_URL/movie/$movieId?api_key=$API_KEY")
        val httpResponse = client.get("$BASE_URL/movie/$movieId?api_key=$API_KEY")
//        if (httpResponse.status.value in 200..299) {
            return httpResponse.body<MovieDetailsResponse>()
//        }
    }
    override suspend fun fetchMovieCredits(movieId: Int): MovieCreditsResponse {
        Log.i("kkDebug", "ZERO$movieId")
        Log.i("kkDebug", "$BASE_URL/movie/$movieId/credits?api_key=$API_KEY")
        val httpResponse = client.get("$BASE_URL/movie/$movieId/credits?api_key=$API_KEY")
//        if (httpResponse.status.value in 200..299) {
            return httpResponse.body<MovieCreditsResponse>()
//        }
    }
}
