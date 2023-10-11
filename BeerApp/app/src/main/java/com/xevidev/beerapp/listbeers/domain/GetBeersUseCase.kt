package com.xevidev.beerapp.listbeers.domain

import com.xevidev.beerapp.listbeers.data.BeersRepository
import com.xevidev.beerapp.listbeers.domain.model.Beer
import javax.inject.Inject

class GetBeersUseCase @Inject constructor(private val repository: BeersRepository) {
    suspend operator fun invoke(): List<Beer> {
        return repository.getBeersList()
    }
}