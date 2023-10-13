package com.xevidev.beerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.xevidev.beerapp.listbeers.domain.model.Routes
import com.xevidev.beerapp.listbeers.ui.BeerListViewModel
import com.xevidev.beerapp.listbeers.ui.BeerSingleScreen
import com.xevidev.beerapp.listbeers.ui.BeersListScreen
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
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.ListBeers.route
                    ) {
                        composable(Routes.ListBeers.route) {
                            BeersListScreen(beerListViewModel,navigationController)
                        }
                        composable(Routes.SingleBeer.route, arguments = listOf(navArgument("id"){ type = NavType.StringType})){ backStrackEntry ->

                            BeerSingleScreen(navigationController, backStrackEntry.arguments?.getString("id").orEmpty(), beerListViewModel)}
                    }
                }
            }
        }
    }
}
