package screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import data.dao.CharactersDao
import data.models.CharacterModel
import kotlinx.coroutines.launch
import model.ResultsModel
import navigation.CharactesScreens
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinInject(),
    charactersDao: CharactersDao
) {
    val scope = rememberCoroutineScope()
    val characters by viewModel.characters.collectAsState(listOf())
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
        LazyColumn {
            items(characters) { item ->
                CardCharacter(item, navController, viewModel)
            }
            item {
                Column(
                    modifier = Modifier.padding(top = 16.dp, bottom = 50.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        onClick = {
                            viewModel.nextPage()
                        }) {
                        Text("Next page")
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        onClick = {
                            viewModel.backPage()
                        }) {
                        Text("Back page")
                    }
                }
            }
        }
    }
}

@Composable
fun CardCharacter(
    item: ResultsModel,
    navController: NavController,
    viewModel: HomeViewModel,
) {
    val favorite = remember {
        mutableStateOf(false)
    }
    favorite.value = item.isFavorite
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(12.dp)
            .clickable {
                viewModel.navigateToDetail(item)
                navController.navigate(CharactesScreens.DETAILS_NAV_TAG)
            }
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            AsyncImage(
                model = item.image,
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape)
            )
            Column {
                Text(
                    text = item.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(100.dp)
                )
            }
            Icon(
                imageVector =
                if (favorite.value)
                    Icons.Filled.Favorite
                else
                    Icons.Filled.FavoriteBorder,
                contentDescription = "favorite",
                modifier = Modifier.clickable {
                    favorite.value = true
                    viewModel.addFavorite(CharacterModel(item))
                }
            )
        }
    }
}