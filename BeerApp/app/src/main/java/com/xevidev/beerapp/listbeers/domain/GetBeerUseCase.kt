package com.xevidev.beerapp.listbeers.domain

import com.xevidev.beerapp.listbeers.data.BeersRepository
import com.xevidev.beerapp.listbeers.domain.model.Beer
import javax.inject.Inject

class GetBeerUseCase @Inject constructor(private val repository: BeersRepository) {
    suspend operator fun invoke(id:Int): List<Beer> {
        return repository.getBeer(id)
    }
}