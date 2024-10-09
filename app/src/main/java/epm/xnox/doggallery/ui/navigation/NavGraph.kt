package epm.xnox.doggallery.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import epm.xnox.doggallery.presentation.detail.ui.DetailScreen
import epm.xnox.doggallery.presentation.detail.viewModel.DetailViewModel
import epm.xnox.doggallery.presentation.home.ui.HomeScreen
import epm.xnox.doggallery.presentation.home.viewModel.HomeViewModel

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = NavRoutes.HomeScreen.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(viewModel = homeViewModel, navController)
        }

        composable(route = NavRoutes.DetailScreen.route) {
            val detailViewModel = hiltViewModel<DetailViewModel>()
            DetailScreen(navController, viewModel = detailViewModel)
        }
    }
}