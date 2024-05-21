package navigation

sealed class CharactesScreens(val route: String) {
    companion object {
        const val HOME_NAV_TAG = "home_nav_tag"
        const val DETAILS_NAV_TAG = "details_nav_tag"
    }

    object Home : CharactesScreens(HOME_NAV_TAG)
    object Details : CharactesScreens(DETAILS_NAV_TAG)
}