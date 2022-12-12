package agency.five.codebase.android.movieapp

import agency.five.codebase.android.movieapp.data.di.dataModule
import agency.five.codebase.android.movieapp.data.di.databaseModule
import agency.five.codebase.android.movieapp.data.network.networkModule
import agency.five.codebase.android.movieapp.ui.favorites.di.favoritesModule
import agency.five.codebase.android.movieapp.ui.home.di.homeModule
import agency.five.codebase.android.movieapp.ui.moviedetails.di.movieDetailsModule
import android.app.Application
import android.util.Log
import org.koin.core.context.startKoin

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("MovieApp", "App started")
        startKoin {
            modules(
                networkModule,
                databaseModule,
                dataModule,
                favoritesModule,
                movieDetailsModule,
                homeModule
            )
        }
    }
}
