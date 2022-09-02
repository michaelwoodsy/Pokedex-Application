package com.example.seng440assignment1.screens.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.seng440assignment1.R

@Composable
fun AboutScreen() {
    LocalConfiguration.current
    val scrollState = rememberScrollState()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokeguide),
                contentDescription = "Pokemon",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .shadow(10.dp, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colors.surface)
            ) {
                Text(
                    text = stringResource(id = R.string.pokeguide_about),
                    modifier = Modifier
                        .padding(16.dp)
                )
                Text(
                    text = stringResource(id = R.string.pokeguide_info),
                    modifier = Modifier
                        .padding(16.dp)
                )
                IntentButton()
            }
        }
    }
}

@Composable
fun IntentButton() {
    val context = LocalContext.current
    val pokemonWebsiteUrl = stringResource(id = R.string.pokemon_website)
    val intent = remember {
        Intent(Intent.ACTION_VIEW, Uri.parse(pokemonWebsiteUrl))
    }
    
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = {
        context.startActivity(intent)
    })
    {
        Text(text = stringResource(id = R.string.visit_website))
    }
}