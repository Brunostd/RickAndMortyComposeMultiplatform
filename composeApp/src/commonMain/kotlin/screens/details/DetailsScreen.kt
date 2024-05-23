package screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import org.koin.compose.koinInject

@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: DetailsViewModel = koinInject()
) {
    val teste by viewModel.character.collectAsState()
    Box{
        Row(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.TopStart)
                .clickable {
                    navController.popBackStack()
                }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "voltar"
            )
            Text(
                text = "VOLTAR",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Text(
                text = teste?.name ?: "Carregando...",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            teste?.image?.let { image ->
                AsyncImage(
                    model = image,
                    contentDescription = teste?.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(250.dp)
                        .height(250.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
}