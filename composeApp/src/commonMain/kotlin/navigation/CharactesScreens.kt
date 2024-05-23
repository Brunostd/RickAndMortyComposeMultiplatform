package navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class CharactesScreens(val route: String, val icon: ImageVector, val label: String) {
    companion object {
        const val HOME_NAV_TAG = "home_nav_tag"
        const val DETAILS_NAV_TAG = "details_nav_tag"
        const val FAVORITE_NAV_TAG = "favorite_nav_tag"
    }

    object Home : CharactesScreens(HOME_NAV_TAG, Icons.Default.Home, "Home")
    object Details : CharactesScreens(DETAILS_NAV_TAG, Icons.Default.Done, "Details")
    object Favorite : CharactesScreens(FAVORITE_NAV_TAG, Icons.Default.Favorite, "Favorite")
}