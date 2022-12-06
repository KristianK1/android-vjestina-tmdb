package agency.five.codebase.android.movieapp.ui.moviedetails.di

import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailsModule = module {
    viewModel {
        MovieDetailsViewModel(
            movieId = 0,
            movieRepository = get(),
            movieDetailsScreenMapper = get(),
        )
    }
    single<MovieDetailsMapper> { MovieDetailsMapperImpl() }
}
