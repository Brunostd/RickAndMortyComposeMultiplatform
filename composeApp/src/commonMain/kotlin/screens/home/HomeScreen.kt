package screens.home

import Greeting
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import model.ResultsModel
import navigation.CharactesScreens
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinInject()
) {
    val characters by viewModel.characters.collectAsState(listOf())
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(characters) { item ->
                Text(
                    text = item.name,
                    modifier = Modifier.clickable {
                        navController.navigate(CharactesScreens.DETAILS_NAV_TAG)
                    }
                )
            }
        }
        Column(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    viewModel.nextPage()
            }) {
                Text("próxima pagina")
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    viewModel.backPage()
                }) {
                Text("voltar uma pagina")
            }
        }
    }
}