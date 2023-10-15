package com.xevidev.beerapp.listbeers.domain

import com.xevidev.beerapp.listbeers.data.BeersRepository
import com.xevidev.beerapp.listbeers.domain.model.Beer
import javax.inject.Inject

class GetSearchBeersUseCase @Inject constructor(private val repository: BeersRepository) {
    suspend operator fun invoke(beer_name: String): List<Beer> {
        return repository.getSearchBeersList(beer_name)
    }
}