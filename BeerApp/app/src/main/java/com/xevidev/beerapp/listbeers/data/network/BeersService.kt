package com.xevidev.beerapp.listbeers.data.network

import com.xevidev.beerapp.listbeers.data.model.BeerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BeersService @Inject constructor(private val api:BeersApiClient){
    suspend fun getBeersList(): List<BeerModel>{
        return withContext(Dispatchers.IO){
            val response = api.getBeersList()
            response.body() ?: emptyList()
        }
    }

    suspend fun getSearchBeersList(beer_name:String):List<BeerModel>{
        return withContext(Dispatchers.IO){
            val response = api.getSearchBeersList(beer_name)
            response.body() ?: emptyList()
        }
    }
}