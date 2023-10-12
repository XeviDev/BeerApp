package com.xevidev.beerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xevidev.beerapp.listbeers.ui.BeerListViewModel
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
//                    color = Color.White
                    color = MyColors.PRIMARY.color
                ) {
                    BeersListScreen(beerListViewModel)
                }
            }
        }
    }
}
