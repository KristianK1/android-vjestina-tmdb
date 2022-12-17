package agency.five.codebase.android.movieapp.ui.main

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.navigation.*
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesRoute
import agency.five.codebase.android.movieapp.ui.favorites.FavoritesViewModel
import agency.five.codebase.android.movieapp.ui.home.HomeRoute
import agency.five.codebase.android.movieapp.ui.home.HomeViewModel
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsRoute
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewModel
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showBottomBar by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route == HOME_ROUTE || navBackStackEntry?.destination?.route == FAVORITES_ROUTE
        }
    }
    val showBackIcon = !showBottomBar

    Scaffold(
        topBar = {
            TopBar(
                navigationIcon = {
                    if (showBackIcon) BackIcon(
                        onBackClick = { navController.popBackStack() },
                        modifier = Modifier.padding(
                            horizontal = MaterialTheme.spacing.medium,
                            vertical = MaterialTheme.spacing.small
                        )
                    )
                }
            )
        },
        bottomBar = {
            if (showBottomBar)
                BottomNavigationBar(
                    destinations = listOf(
                        NavigationItem.HomeDestination,
                        NavigationItem.FavoritesDestination,
                    ),
                    onNavigateToDestination = { route ->
                        navController.navigate(route.route) {
                            popUpTo(route.route) {
                                inclusive = true
                            }
                        }
                    },
                    currentDestination = navBackStackEntry?.destination
                )
        }
    ) { padding ->
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.HomeDestination.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(NavigationItem.HomeDestination.route) {
                    HomeRoute(
                        onNavigateToMovieDetails = { route ->
                            navController.navigate(route)
                        },
                        viewModel = getViewModel<HomeViewModel>(),
                    )
                }
                composable(NavigationItem.FavoritesDestination.route) {
                    FavoritesRoute(
                        onNavigateToMovieDetails = { route ->
                            navController.navigate(route)
                        },
                        viewModel = getViewModel<FavoritesViewModel>(),
                    )
                }
                composable(
                    route = MovieDetailsDestination.route,
                    arguments = listOf(navArgument(MOVIE_ID_KEY) { type = NavType.IntType }),
                ) { navBackStackEntry ->
                    MovieDetailsRoute(
                        viewModel = getViewModel {
                            parametersOf(
                                navBackStackEntry.arguments?.getInt(MOVIE_ID_KEY)?: throw Throwable("no movieId sent")
                            )
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBar(
    navigationIcon: @Composable (() -> Unit)? = null,
) {
    Surface(
        color = Color.Blue
    ) {
        if (navigationIcon != null) navigationIcon()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.TopbarHeight))
        ) {
            Image(
                painter = painterResource(id = R.drawable.tmdb_logo),
                contentDescription = "app logo",
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun BackIcon(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Image(painter = painterResource(
        id = R.drawable.backicon
    ),
        contentDescription = "back button",
        modifier = modifier
            .clickable { onBackClick() }
    )
}

@Composable
private fun BottomNavigationBar(
    destinations: List<NavigationItem>,
    onNavigateToDestination: (NavigationItem) -> Unit,
    currentDestination: NavDestination?,
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
    ) {
        destinations.forEach { destination ->
            run {
                BottomNavigationItem(
                    selected = currentDestination?.route == destination.route,
                    icon = {
                        Icon(
                            painter = painterResource(if (currentDestination?.route == destination.route) destination.selectedIconId else destination.unselectedIconId),
                            contentDescription = "icon"
                        )
                    },
                    label = {
                        Text(text = stringResource(id = destination.labelId))
                    },
                    onClick = { onNavigateToDestination(destination) }
                )
            }
        }
    }
}
