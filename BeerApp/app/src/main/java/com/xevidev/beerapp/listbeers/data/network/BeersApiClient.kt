package com.xevidev.beerapp.listbeers.data.network

import com.xevidev.beerapp.listbeers.data.model.BeerModel
import retrofit2.Response
import retrofit2.http.GET

interface BeersApiClient {
    @GET("beers")
    suspend fun getBeersList(): Response<List<BeerModel>>
}