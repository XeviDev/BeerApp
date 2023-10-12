package com.xevidev.beerapp.listbeers.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.*
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

@Composable
fun BeersListScreen(beerListViewModel: BeerListViewModel) {

    /*val beers by beerListViewModel.beers.collectAsState()

    LaunchedEffect(Unit) {

        beerListViewModel.getBeers()

    }
    Column {
        if (beers.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(beers) { beer ->
                    Text(text = beer.id.toString())
                    Text(text = beer.name)
                    Text(text = beer.description)
                    Text(text = beer.tagline)
                    Divider()
                }

            }
        }
    }*/
    addSearchBar(beerListViewModel)


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addSearchBar(beerListViewModel: BeerListViewModel) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(modifier = Modifier.fillMaxWidth(),
        query = query, onQueryChange = {
            query = it
        }, onSearch = {
            active = false
        }, active = active, onActiveChange = { active = it }) {
        beerListViewModel.getSearchBeers(query)
        val beers by beerListViewModel.beers.collectAsState()
        LazyColumn {
            items(beers) { beer ->
                Text(text = beer.id.toString())
                Text(text = beer.name)
                Text(text = beer.description)
                Text(text = beer.tagline)
                Divider()
            }
        }

    }

}