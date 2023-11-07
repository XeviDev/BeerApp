package com.xevidev.beerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import com.xevidev.beerapp.listbeers.ui.BeerListViewModel
import com.xevidev.beerapp.listbeers.ui.NavGraphs
import com.xevidev.beerapp.listbeers.ui.destinations.BeersListScreenDestination
import com.xevidev.beerapp.listbeers.ui.utils.MyColors
import com.xevidev.beerapp.ui.theme.BeerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val beerListViewModel: BeerListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeerAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MyColors.PRIMARY.color
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root,
                        dependenciesContainerBuilder = {
                            dependency(BeersListScreenDestination){beerListViewModel}
                        })
                }
            }
        }
    }
}
