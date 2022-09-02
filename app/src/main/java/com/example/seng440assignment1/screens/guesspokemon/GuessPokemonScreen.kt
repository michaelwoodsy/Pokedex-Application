package com.example.seng440assignment1.screens.guesspokemon

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.seng440assignment1.R
import com.example.seng440assignment1.data.models.PokedexListEntry
import com.example.seng440assignment1.util.parseNameToCleanName
import com.example.seng440assignment1.viewmodel.GuessPokemonViewModel

@Composable
fun GuessPokemonScreen(
    viewModel: GuessPokemonViewModel = hiltViewModel(),
) {
    val configuration = LocalConfiguration.current
    val pokemon by remember { viewModel.randomPokemon }
    val blendMode = remember { viewModel.blendMode }
    val score = remember { viewModel.score }
    val scrollState = rememberScrollState()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        when (configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pokequiz),
                        contentDescription = "Pokemon",
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp)
                    )
                    GuessSection(
                        hint = stringResource(id = R.string.guess_pokemon_textfield),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        pokemon = pokemon,
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .shadow(10.dp, RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.colors.surface)
                    ) {
                        DropDownMenu()
                        Text(
                            text = stringResource(id = R.string.current_score) + " ${score.value}",
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(16.dp)
                        ) {
                            SubcomposeAsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(pokemon.imageUrl)
                                    .build(),
                                contentDescription = pokemon.pokemonName,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(240.dp)
                                    .align(Alignment.Center),
                                loading = {
                                    CircularProgressIndicator(
                                        color = MaterialTheme.colors.primary,
                                        modifier = Modifier.scale(0.5f)
                                    )
                                },
                                colorFilter = ColorFilter.tint(
                                    color = MaterialTheme.colors.onSurface,
                                    blendMode = blendMode.value
                                )
                            )
                        }
                    }
                }
            }
            else -> {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.4f)
                                .align(Alignment.CenterStart)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pokequiz),
                                contentDescription = "Pokemon",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                                    .padding(16.dp)
                            )
                            GuessSection(
                                hint = stringResource(id = R.string.guess_pokemon_textfield),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                pokemon = pokemon,
                            )
                        }
                        Column(modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .align(Alignment.CenterEnd)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                                    .shadow(10.dp, RoundedCornerShape(10.dp))
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(MaterialTheme.colors.surface)
                            ) {
                                DropDownMenu()
                                Text(
                                    text = stringResource(id = R.string.current_score) + " ${score.value}",
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .align(Alignment.CenterHorizontally)
                                )
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(16.dp)
                                ) {
                                    SubcomposeAsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(pokemon.imageUrl)
                                            .build(),
                                        contentDescription = pokemon.pokemonName,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(180.dp)
                                            .align(Alignment.Center),
                                        loading = {
                                            CircularProgressIndicator(
                                                color = MaterialTheme.colors.primary,
                                                modifier = Modifier.scale(0.5f)
                                            )
                                        },
                                        colorFilter = ColorFilter.tint(
                                            color = MaterialTheme.colors.onSurface,
                                            blendMode = blendMode.value
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GuessSection(
    modifier: Modifier = Modifier,
    hint: String = "",
    pokemon: PokedexListEntry,
    viewModel: GuessPokemonViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val pokemonName = parseNameToCleanName(pokemon.pokemonName)

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    var searchText by remember {
        viewModel.searchText
    }
    val correctText = stringResource(id = R.string.correct)
    val incorrectText = stringResource(id = R.string.incorrect)

    Column {
        Box(modifier = modifier) {
            BasicTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
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
            if (isHintDisplayed && searchText == "") {
                Text(
                    text = hint,
                    color = Color.LightGray,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                )
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                ),
            onClick = {
                if (searchText.lowercase() == pokemonName.lowercase()) {
                    Toast.makeText(context, "$correctText $pokemonName", Toast.LENGTH_SHORT).show()
                    searchText = ""
                    viewModel.addToScore()
                    viewModel.revealPokemonColour()
                } else {
                    Toast.makeText(context, "$incorrectText $pokemonName", Toast.LENGTH_SHORT).show()
                    searchText = ""
                    viewModel.resetScore()
                    viewModel.revealPokemonColour()
                }
            },
        ) {
            Text(text = stringResource(id = R.string.guess_pokemon_button))
        }
    }
}

@Composable
fun DropDownMenu(
    viewModel: GuessPokemonViewModel = hiltViewModel()
) {

    var expanded by remember { mutableStateOf(false) }

    val suggestions = listOf(
        stringResource(id = R.string.all_generations),
        stringResource(id = R.string.generation_1),
        stringResource(id = R.string.generation_2),
        stringResource(id = R.string.generation_3),
        stringResource(id = R.string.generation_4),
        stringResource(id = R.string.generation_5),
        stringResource(id = R.string.generation_6),
        stringResource(id = R.string.generation_7),
        stringResource(id = R.string.generation_8)
    )

    val selectedTextVal = stringResource(id = R.string.all_generations)
    var selectedText by remember { mutableStateOf(selectedTextVal) }
    val generation = remember { viewModel.generation }
    val generationSelect = stringResource(id = R.string.select_generation)
    var textFieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textFieldSize = coordinates.size.toSize()
                },
            label = {Text(generationSelect)},
            readOnly = true,
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textFieldSize.width.toDp()})
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                    generation.value = selectedText
                    viewModel.resetScore()
                    viewModel.getRandomPokemon(generation.value)
                }) {
                    Text(text = label)
                }
            }
        }
    }
}
