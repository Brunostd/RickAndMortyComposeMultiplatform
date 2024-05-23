package screens.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import data.dao.CharactersDao
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun FavoriteScreen(
    navController: NavController,
    charactersDao: CharactersDao,
    viewModel: FavoriteViewModel = koinInject()
) {
    val scope = rememberCoroutineScope()
    val favoriteCharacters by viewModel.favoriteCharacters.collectAsState(listOf())
    LaunchedEffect(true) {
        scope.launch {
            viewModel.initialize(charactersDao)
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.padding(bottom = 60.dp)
        ) {
            items(favoriteCharacters) { item ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 8.dp,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = item.name,
                            fontSize = 22.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                        AsyncImage(
                            model = item.image,
                            contentDescription = item.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(CircleShape)
                        )
                    }
                }
            }
        }
    }
}