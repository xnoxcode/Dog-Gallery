package epm.xnox.doggallery.ui.navigation

sealed class NavRoutes(val route: String) {

    data object HomeScreen : NavRoutes("home")

    data object DetailScreen : NavRoutes("detail")
}
