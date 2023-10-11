package com.xevidev.beerapp.listbeers.data

import com.xevidev.beerapp.listbeers.data.model.BeerModel
import com.xevidev.beerapp.listbeers.data.network.BeersService
import com.xevidev.beerapp.listbeers.domain.model.Beer
import com.xevidev.beerapp.listbeers.domain.model.parseDomain
import javax.inject.Inject

class BeersRepository @Inject constructor(private val api:BeersService){
    suspend fun getBeersList(): List<Beer>{
        val response: List<BeerModel> = api.getBeersList()
        return response.map { it.parseDomain() }
    }

    suspend fun getSearchBeersList(beer_name:String):List<Beer>{
        val response:List<BeerModel> = api.getSearchBeersList(beer_name)
        return response.map { it.parseDomain() }
    }
}