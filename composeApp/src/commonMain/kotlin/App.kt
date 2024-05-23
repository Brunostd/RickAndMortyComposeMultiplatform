import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import androidx.room.RoomDatabase
import data.database.CharacterDataBase
import di.appModule
import navigation.CharactersSetupNavController
import navigation.CharactesScreens
import navigation.bottom.BottomNavigationBar
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import themes.darkTheme
import themes.lightTheme

@Composable
@Preview
fun App(
    databaseBuilder: RoomDatabase.Builder<CharacterDataBase>
) {
    val navController = rememberNavController()
    val useDarkTheme = isSystemInDarkTheme()

    val bottomNavigationItems = listOf(
        CharactesScreens.Home,
        CharactesScreens.Favorite,
    )

    KoinApplication(application = {
        modules(appModule())
    }) {
        MaterialTheme(
            colors = if (useDarkTheme) darkTheme else lightTheme
        ) {
            // Building the database
            val database = remember { databaseBuilder.build() }

            // Creating UserDao instance and passing in other screens
            val characterDao = remember { database.characterDao() }
            Scaffold(
                bottomBar = { BottomNavigationBar(navController, bottomNavigationItems) }
            ) {
                CharactersSetupNavController(navController, characterDao)
            }
        }
    }
}