package epm.xnox.doggallery.presentation.home.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import epm.xnox.doggallery.R
import epm.xnox.doggallery.presentation.home.viewModel.HomeEvent
import epm.xnox.doggallery.presentation.home.viewModel.HomeViewModel
import epm.xnox.doggallery.ui.navigation.NavRoutes
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavHostController) {

    Scaffold(bottomBar = { BottomNavigation() }) {
        Column(modifier = Modifier.fillMaxSize()) {
            Header()
            Category(viewModel)
            Content(viewModel, navController)
        }
    }
}

@Composable
fun BottomNavigation() {
    var selected by remember { mutableStateOf(MenuItem.Home) }

    NavigationBar {
        NavigationBarItem(
            selected = selected == MenuItem.Home,
            onClick = {
                if (selected != MenuItem.Home) {
                    selected = MenuItem.Home
                }
            },
            icon = {
                Icon(
                    imageVector = if (selected == MenuItem.Home) Icons.Default.Home else Icons.Outlined.Home,
                    contentDescription = "Icon home",
                )
            }
        )
        NavigationBarItem(
            selected = selected == MenuItem.Favorite,
            onClick = {
                if (selected != MenuItem.Favorite) {
                    selected = MenuItem.Favorite
                }
            },
            icon = {
                Icon(
                    imageVector = if (selected == MenuItem.Favorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Icon favorite"
                )
            }
        )
        NavigationBarItem(
            selected = selected == MenuItem.Search,
            onClick = {
                if (selected != MenuItem.Search) {
                    selected = MenuItem.Search
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Icon search"
                )
            }
        )
        NavigationBarItem(
            selected = selected == MenuItem.Account,
            onClick = {
                if (selected != MenuItem.Account) {
                    selected = MenuItem.Account
                }
            },
            icon = {
                Icon(
                    imageVector = if (selected == MenuItem.Account) Icons.Default.AccountCircle else Icons.Outlined.AccountCircle,
                    contentDescription = "Icon account"
                )
            }
        )
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
            text = "Dogs Gallery",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.segment), contentDescription = "Icon menu"
            )
        }
    }
}

@Composable
fun Category(viewModel: HomeViewModel) {
    val dogs = mutableStateListOf(
        "affenpinscher",
        "african",
        "airedale",
        "akita",
        "appenzeller",
        "basenji",
        "beagle",
        "bluetick",
        "borzoi",
        "bouvier",
        "boxer",
        "brabancon",
        "briard",
        "cavapoo",
        "chihuahua",
        "chow",
        "clumber",
        "cockapoo",
        "coonhound",
        "cotondetulear",
        "dachshund",
        "dalmatian",
        "dhole",
        "dingo",
        "doberman",
        "entlebucher",
        "eskimo",
        "germanshepherd",
        "groenendael",
        "havanese",
        "husky",
        "keeshond",
        "kelpie",
        "kombai",
        "kuvasz",
        "labradoodle",
        "labrador",
        "leonberg",
        "lhasa",
        "malamute",
        "malinois",
        "maltese",
        "mexicanhairless",
        "mix",
        "newfoundland",
        "otterhound",
        "papillon",
        "pekinese",
        "pembroke",
        "pitbull",
        "pomeranian",
        "pug",
        "puggle",
        "pyrenees",
        "redbone",
        "rottweiler",
        "saluki",
        "samoyed",
        "schipperke",
        "shiba",
        "shihtzu",
        "stbernard",
        "tervuren",
        "vizsla",
        "weimaraner",
        "whippet"
    )
    val randomDogs by remember { mutableStateOf(dogs.shuffled().take(10)) }
    var dogSelected by remember { mutableStateOf("random") }

    LaunchedEffect(dogSelected) {
        if (dogSelected == "random") {
            viewModel.onEvent(HomeEvent.GetRandomDogs(10))
        } else {
            viewModel.onEvent(HomeEvent.GetDogsByRaza(dogSelected))
        }
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        item {
            CategoryItem(name = "random", selected = "random" == dogSelected) { dog ->
                if (dog != dogSelected) {
                    dogSelected = "random"
                }
            }
        }
        item {
            CategoryItem(name = randomDogs[0], selected = randomDogs[0] == dogSelected) { dog ->
                if (dog != dogSelected) {
                    dogSelected = dog
                }
            }
        }
        item {
            CategoryItem(name = randomDogs[1], selected = randomDogs[1] == dogSelected) { dog ->
                if (dog != dogSelected) {
                    dogSelected = dog
                }
            }
        }
        item {
            CategoryItem(name = randomDogs[2], selected = randomDogs[2] == dogSelected) { dog ->
                if (dog != dogSelected) {
                    dogSelected = dog
                }
            }
        }
        item {
            CategoryItem(name = randomDogs[3], selected = randomDogs[3] == dogSelected) { dog ->
                if (dog != dogSelected) {
                    dogSelected = dog
                }
            }
        }
        item {
            CategoryItem(name = randomDogs[4], selected = randomDogs[4] == dogSelected) { dog ->
                if (dog != dogSelected) {
                    dogSelected = dog
                }
            }
        }
        item {
            CategoryItem(name = randomDogs[5], selected = randomDogs[5] == dogSelected) { dog ->
                if (dog != dogSelected) {
                    dogSelected = dog
                }
            }
        }
        item {
            CategoryItem(name = randomDogs[6], selected = randomDogs[6] == dogSelected) { dog ->
                if (dog != dogSelected) {
                    dogSelected = dog
                }
            }
        }
        item {
            CategoryItem(name = randomDogs[7], selected = randomDogs[7] == dogSelected) { dog ->
                if (dog != dogSelected) {
                    dogSelected = dog
                }
            }
        }
        item {
            CategoryItem(name = randomDogs[8], selected = randomDogs[8] == dogSelected) { dog ->
                if (dog != dogSelected) {
                    dogSelected = dog
                }
            }
        }
        item {
            CategoryItem(name = randomDogs[9], selected = randomDogs[9] == dogSelected) { dog ->
                if (dog != dogSelected) {
                    dogSelected = dog
                }
            }
        }
    }
}

@Composable
fun CategoryItem(name: String, selected: Boolean, onSelected: (name: String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .clickable {
                onSelected(name.replaceFirstChar { if (it.isUpperCase()) it.lowercase(Locale.getDefault()) else it.toString() })
            }
    ) {
        Text(
            text = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            fontWeight = if (selected) FontWeight.ExtraBold else null,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        if (selected) {
            Box(
                modifier = Modifier
                    .size(5.dp)
                    .background(
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun Content(viewModel: HomeViewModel, navController: NavHostController) {
    val lazyState = rememberLazyStaggeredGridState()
    val state = viewModel.state.value

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        if (state.error.isNotBlank()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Error", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = state.error,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyVerticalStaggeredGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                state = lazyState,
                columns = StaggeredGridCells.Fixed(2),
                content = {
                    items(state.data.images) { image ->
                        DogItem(image) { url ->
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                key = "images",
                                value = state.data.images,
                            )
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                key = "url",
                                value = url,
                            )
                            navController.navigate(NavRoutes.DetailScreen.route)
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun DogItem(url: String, onClick: (url: String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable { onClick(url) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}

enum class MenuItem {
    Home, Favorite, Account, Search
}