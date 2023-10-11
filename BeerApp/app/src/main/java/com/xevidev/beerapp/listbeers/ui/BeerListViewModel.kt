package com.xevidev.beerapp.listbeers.ui

import androidx.lifecycle.ViewModel
import com.xevidev.beerapp.listbeers.domain.GetBeersUseCase
import javax.inject.Inject

class BeerListViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersUseCase
):ViewModel() {
    
}