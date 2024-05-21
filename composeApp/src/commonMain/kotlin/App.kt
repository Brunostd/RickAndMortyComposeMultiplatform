import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import di.appModule
import navigation.CharactersSetupNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.dsl.module

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    KoinApplication(application = {
        modules(appModule())
    }) {
        MaterialTheme {
            CharactersSetupNavController(navController)
        }
    }
}