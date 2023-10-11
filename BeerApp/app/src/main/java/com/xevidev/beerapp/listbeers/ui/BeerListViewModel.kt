package com.xevidev.beerapp.listbeers.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xevidev.beerapp.listbeers.domain.GetBeersUseCase
import com.xevidev.beerapp.listbeers.domain.model.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(
    private val getBeersUseCase: GetBeersUseCase
):ViewModel() {
    private val _beers = MutableStateFlow(emptyList<Beer>())
    val beers: StateFlow<List<Beer>> = _beers

    fun getBeers(){
       viewModelScope.launch {
           try {
               _beers.value = getBeersUseCase()
           }catch (e: Exception){

           }
       }
    }
}