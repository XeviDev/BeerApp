package com.xevidev.beerapp.listbeers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.xevidev.beerapp.listbeers.domain.model.Beer
import com.xevidev.beerapp.listbeers.ui.utils.MyColors

@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerSingleScreen(
    navigator: DestinationsNavigator,
    beer: Beer
) {

    Column(Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(title = { Text(text = "BeerApp") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MyColors.SECONDARY.color,
                titleContentColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick = { navigator.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = ""
                    )
                }
            })
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            BeerImg(img = beer.imageUrl)
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(shape = RoundedCornerShape(32.dp, 32.dp, 0.dp, 0.dp)),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(MyColors.TERTIARY.color)
            ) {
                BeerInfo(beer.name, beer.description, beer.abv)
            }
        }
    }
}

@Composable
fun BeerInfo(name: String, description: String, abv: String) {
    Box(Modifier.padding(top = 24.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 32.sp
            )
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                fontWeight = FontWeight.Bold,
                text = "$abv% ABV",
                color = Color.White
            )
            Text(text = description, color = MyColors.PRIMARY.color)
        }
    }
}

@Composable
fun BeerImg(img: String) {
    AsyncImage(
        model = img,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxWidth()
    )
}
