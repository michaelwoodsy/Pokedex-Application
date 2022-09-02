package com.example.seng440assignment1.screens.pokemonlist

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.seng440assignment1.R
import com.example.seng440assignment1.data.models.PokedexListEntry
import com.example.seng440assignment1.ui.theme.RobotoCondensed
import com.example.seng440assignment1.util.parseNameToCleanName
import com.example.seng440assignment1.viewmodel.PokemonListViewModel

@Composable
fun PokemonListScreen(
    navController: NavHostController,
    viewModel: PokemonListViewModel = hiltViewModel(),
) {
    val configuration = LocalConfiguration.current
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        when (configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.pokedex),
                        contentDescription = "Pokemon",
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(CenterHorizontally)
                            .padding(16.dp)
                    )
                    SearchBar(
                        hint = stringResource(id = R.string.search_hint),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        viewModel.searchPokemonList(it)
                    }
                    PokemonList(navController = navController)
                }
            }
            else -> {
                Column {
                    Box(
                        modifier = Modifier
                        .fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = R.drawable.pokedex),
                            contentDescription = "Pokemon",
                            modifier = Modifier
                                .fillMaxWidth(0.4f)
                                .align(CenterStart)
                                .padding(16.dp)
                        )
                        SearchBar(
                            hint = stringResource(id = R.string.search_hint),
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .align(CenterEnd)
                                .padding(16.dp),
                        ) {
                            viewModel.searchPokemonList(it)
                        }
                    }
                    PokemonList(navController = navController)
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    viewModel: PokemonListViewModel = hiltViewModel(),
    onSearch: (String) -> Unit = {},
) {
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    var searchText by remember {
        viewModel.searchText
    }

    Box(modifier = modifier){
        BasicTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                onSearch(searchText)
            },
            maxLines = 1,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true
                }
        )
        if(isHintDisplayed && searchText == ""){
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }

    }
}

@Composable
fun PokemonList(
    navController: NavHostController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pokemonList by remember { viewModel.pokemonList }
    val endReached by remember { viewModel.endReached }
    val isLoading by remember { viewModel.isLoading }
    val isSearching by remember { viewModel.isSearching }

    if (isLoading) {
        Box(modifier = Modifier
            .fillMaxSize()) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .fillMaxSize(0.5f)
                    .align(Center)
            )
        }
    } else {
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            val itemCount = pokemonList.size
            items(itemCount) {
                if(it >= itemCount -1 && !endReached && !isLoading && !isSearching) {
                    LaunchedEffect(key1 = true) {
                        viewModel.loadPokemon()
                    }
                }
                PokedexRow(rowIndex = it, entries = pokemonList, navController = navController)
            }
        }
    }
}

@Composable
fun PokedexEntry(
    entry: PokedexListEntry,
    navController: NavHostController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    val pokemonName = parseNameToCleanName(entry.pokemonName)
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            Row (
                modifier = Modifier
                    .shadow(5.dp, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                dominantColor,
                                defaultDominantColor
                            )
                        )
                    )
                    .clickable {
                        navController.navigate(
                            "pokemon_details_screen/${dominantColor.toArgb()}/${entry.pokemonName}"
                        )
                    },
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(entry.imageUrl)
                        .build(),
                    contentDescription = entry.pokemonName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp),
                    onSuccess = { success ->
                        val drawable = success.result.drawable
                        viewModel.calcDominantColor(drawable) { color ->
                            dominantColor = color
                        }
                    },
                    loading = {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.scale(0.5f)
                        )
                    },
                )
                Text(
                    text = pokemonName,
                    fontFamily = RobotoCondensed,
                    fontSize = 36.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterVertically),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        else -> {
            Row (
                modifier = Modifier
                    .shadow(5.dp, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                dominantColor,
                                defaultDominantColor
                            )
                        )
                    )
                    .clickable {
                        navController.navigate(
                            "pokemon_details_screen/${dominantColor.toArgb()}/${entry.pokemonName}"
                        )
                    },
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(entry.imageUrl)
                        .build(),
                    contentDescription = entry.pokemonName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp),
                    onSuccess = { success ->
                        val drawable = success.result.drawable
                        viewModel.calcDominantColor(drawable) { color ->
                            dominantColor = color
                        }
                    },
                    loading = {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier.scale(0.5f)
                        )
                    }
                )
                Text(
                    text = pokemonName,
                    fontFamily = RobotoCondensed,
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterVertically),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Composable
fun PokedexRow(
    rowIndex: Int,
    entries: List<PokedexListEntry>,
    navController: NavHostController
) {
    Row {
        PokedexEntry(
            entry = entries[rowIndex],
            navController = navController,
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}