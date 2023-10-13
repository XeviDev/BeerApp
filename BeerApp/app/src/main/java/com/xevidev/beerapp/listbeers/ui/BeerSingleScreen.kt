package com.xevidev.beerapp.listbeers.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController

@Composable
fun BeerSingleScreen(
    navigationController: NavHostController,
    id: String,
    beerListViewModel: BeerListViewModel
) {
    beerListViewModel.getBeer(id.toInt())
    val beer by beerListViewModel.beers.collectAsState()
    Text(text = beer.first().id.toString())
    Text(text = beer.first().name)
    Text(text = beer.first().description)
    Text(text = beer.first().abv)
}