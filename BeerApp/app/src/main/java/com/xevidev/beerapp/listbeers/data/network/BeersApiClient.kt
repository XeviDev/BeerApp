package com.xevidev.beerapp.listbeers.data.network

import com.xevidev.beerapp.listbeers.data.model.BeerModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeersApiClient {
    @GET("beers")
    suspend fun getBeersList(): Response<List<BeerModel>>
    @GET("beers")
    suspend fun getSearchBeersList(@Query("beer_name") beer_name:String): Response<List<BeerModel>>
}