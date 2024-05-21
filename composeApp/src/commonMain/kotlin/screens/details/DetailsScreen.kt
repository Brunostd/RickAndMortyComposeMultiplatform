package screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun DetailsScreen(
    navController: NavController
) {
    Box{
        Text(
            text = "voltar",
            modifier = Modifier
                .align(Alignment.TopStart)
                .clickable {
                    navController.popBackStack()
                }
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Text("Tela de detalhes")
        }
    }
}