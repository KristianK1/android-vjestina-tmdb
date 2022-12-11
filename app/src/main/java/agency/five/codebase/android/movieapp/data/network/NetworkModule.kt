package agency.five.codebase.android.movieapp.data.network

import io.ktor.client.*
import io.ktor.client.engine.android.*
import org.koin.dsl.module

val networkModule = module {
    single<MovieService> { MovieServiceImpl(client = get()) }
    single {
        HttpClient(Android) {
            // your code goes here
        }
    }
}
