package epm.xnox.doggallery.presentation.detail.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import epm.xnox.doggallery.R
import epm.xnox.doggallery.presentation.detail.viewModel.DetailViewModel

@Composable
fun DetailScreen(backStackEntry: NavHostController, viewModel: DetailViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Header()
        Content(backStackEntry)
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Detalles",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Icon favorite"
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Content(navController: NavHostController) {
    val imagesResult =
        navController.previousBackStackEntry?.savedStateHandle?.get<List<String>>("images")
    val positionImageResult =
        navController.previousBackStackEntry?.savedStateHandle?.get<String>("url")

    val images by remember { mutableStateOf(imagesResult) }

    val pageState = rememberPagerState(
        pageCount = { images!!.size },
        initialPage = images?.indexOf(positionImageResult) ?: 0
    )

    HorizontalPager(state = pageState) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(images!![it])
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}