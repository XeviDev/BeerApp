package com.xevidev.beerapp.listbeers.domain.model

sealed class Routes(val route:String) {
    object ListBeers:Routes("BeersListScreen")
    object SingleBeer:Routes("BeerScreen/{id}")

}