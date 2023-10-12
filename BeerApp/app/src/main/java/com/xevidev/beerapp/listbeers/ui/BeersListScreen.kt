package com.xevidev.beerapp.listbeers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.xevidev.beerapp.listbeers.domain.model.Beer

@Composable
fun BeersListScreen(beerListViewModel: BeerListViewModel) {
    Column {
        searchBar2(beerListViewModel)
        val beers by beerListViewModel.beers.collectAsState()
        LazyColumn(
            Modifier
                .padding(top = 8.dp)
        ) {
            items(beers) { beer ->
                SingleItem(beer)
            }
        }
    }


}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun searchBar2(beerListViewModel: BeerListViewModel) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        value = query,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        onValueChange = {
            query = it
            beerListViewModel.getSearchBeers(query)
        },
        placeholder = { Text(text = "Search") },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
            }
        ))

}

@Composable
fun SingleItem(beer: Beer) {
    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(contentColor = Color.White, containerColor = Color.Gray)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = beer.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)

            )
            Column(
                modifier = Modifier.padding(start = 12.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(text = beer.name, modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis, maxLines = 1)
                Text(text = beer.description, overflow = TextOverflow.Ellipsis, maxLines = 2, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))
                Text(text = beer.abv+"% ABV", modifier = Modifier.fillMaxWidth(), overflow = TextOverflow.Ellipsis, maxLines = 1)
            }
        }


    }

}

