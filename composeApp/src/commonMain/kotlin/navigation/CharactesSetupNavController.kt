package navigation

import screens.details.DetailsScreen
import screens.home.HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun CharactersSetupNavController(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = CharactesScreens.HOME_NAV_TAG
    ) {
        composable(CharactesScreens.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(CharactesScreens.Details.route) {
            DetailsScreen(navController = navController)
        }
    }
}