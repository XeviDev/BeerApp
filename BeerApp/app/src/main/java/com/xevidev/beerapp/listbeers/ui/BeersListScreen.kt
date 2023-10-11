package com.xevidev.beerapp.listbeers.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BeersListScreen(beerListViewModel: BeerListViewModel) {

    val beers by beerListViewModel.beers.collectAsState()

    LaunchedEffect(Unit) {

        beerListViewModel.getBeers()

    }
    Column{
        if (beers.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(beers){
                        beer ->
                    Text(text = beer.id.toString())
                    Text(text = beer.name)
                    Text(text = beer.description)
                    Text(text = beer.tagline)
                    Divider()
                }

            }
        }
    }


}