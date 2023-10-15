package com.xevidev.beerapp.listbeers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xevidev.beerapp.listbeers.domain.GetBeerUseCase
import com.xevidev.beerapp.listbeers.domain.GetBeersUseCase
import com.xevidev.beerapp.listbeers.domain.GetSearchBeersUseCase
import com.xevidev.beerapp.listbeers.domain.model.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersUseCase,
    private val getSearchBeersUseCase: GetSearchBeersUseCase,
    private val getBeerUseCase: GetBeerUseCase
) : ViewModel() {

    private val _beers = MutableStateFlow(emptyList<Beer>())
    val beers: StateFlow<List<Beer>> = _beers

    fun getSearchBeers(beer_name: String) {
        viewModelScope.launch {
            try {
                _beers.value = getSearchBeersUseCase(beer_name)
            } catch (e: Exception) {

            }
        }
    }

    fun getBeer(id: Int) {
        viewModelScope.launch {
            try {
                _beers.value = getBeerUseCase(id)
            } catch (e: Exception) {

            }
        }
    }
}