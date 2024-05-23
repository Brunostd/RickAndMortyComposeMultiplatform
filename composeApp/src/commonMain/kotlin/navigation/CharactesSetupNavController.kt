package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import data.dao.CharactersDao
import screens.details.DetailsScreen
import screens.favorite.FavoriteScreen
import screens.home.HomeScreen

@Composable
fun CharactersSetupNavController(
    navController: NavHostController,
    charactersDao: CharactersDao
) {
    NavHost(
        navController = navController,
        startDestination = CharactesScreens.HOME_NAV_TAG
    ) {
        composable(CharactesScreens.Home.route) {
            HomeScreen(navController = navController, charactersDao = charactersDao)
        }
        composable(CharactesScreens.Details.route) {
            DetailsScreen(navController = navController)
        }
        composable(CharactesScreens.Favorite.route) {
            FavoriteScreen(navController = navController, charactersDao)
        }
    }
}