package com.xevidev.beerapp.listbeers.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.xevidev.beerapp.listbeers.domain.model.Beer

@Composable
fun BeersListScreen(beerListViewModel: BeerListViewModel) {
    //searchBar1(beerListViewModel)
    Column {
        searchBar2(beerListViewModel)
        val beers by beerListViewModel.beers.collectAsState()
        LazyColumn(Modifier.padding(top = 8.dp)) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchBar1(beerListViewModel: BeerListViewModel) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(modifier = Modifier.wrapContentHeight(),
        placeholder = { Text(text = "Search") },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
        query = query, onQueryChange = {
            query = it
        }, onSearch = {
            active = false
        }, active = active, onActiveChange = { active = it }) {
        beerListViewModel.getSearchBeers(query)
        val beers by beerListViewModel.beers.collectAsState()
        LazyColumn {
            items(beers) { beer ->
                SingleItem(beer)
            }
        }

    }
}

@Composable
fun SingleItem(beer: Beer) {
    Card(Modifier.padding(8.dp)) {
        AsyncImage(
            model = beer.imageUrl,
            contentDescription = null
        )
        Text(text = beer.id.toString())
        Text(text = beer.name)
        Text(text = beer.description, overflow = TextOverflow.Ellipsis, maxLines = 1)
        Text(text = beer.tagline)
    }

}
