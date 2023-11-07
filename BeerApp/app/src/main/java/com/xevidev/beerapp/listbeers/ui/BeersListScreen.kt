package com.xevidev.beerapp.listbeers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.xevidev.beerapp.listbeers.domain.model.Beer
import com.xevidev.beerapp.listbeers.ui.destinations.BeerSingleScreenDestination
import com.xevidev.beerapp.listbeers.ui.utils.MyColors

@RootNavGraph(start = true)
@Destination
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BeersListScreen(
    beerListViewModel: BeerListViewModel,
    navigator: DestinationsNavigator
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column(
        Modifier
            .padding(8.dp)
            .background(MyColors.PRIMARY.color)
    ) {
        SearchBar(beerListViewModel, keyboardController, focusManager)
        val beers by beerListViewModel.beers.collectAsState()
        if (beers.isEmpty()) {
            Nothing()
        } else {
            LazyColumn(
                Modifier
                    .padding(top = 8.dp)
            ) {
                items(beers) { beer ->
                    SingleItem(beer, keyboardController, focusManager, navigator)
                }

            }
        }
    }


}

@Composable
fun Nothing() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                Modifier
                    .size(32.dp)
                    .fillMaxWidth(),
                tint = Color.Gray
            )
            Text(
                text = "Empty result",
                color = Color.Gray,
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    beerListViewModel: BeerListViewModel,
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager
) {
    var query by rememberSaveable { mutableStateOf("") }
    if (query.isNotEmpty()) beerListViewModel.getSearchBeers(query)
    TextField(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        value = query,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = MyColors.GREY_PRIMARY.color,
        ),
        onValueChange = {
            query = it
            beerListViewModel.getSearchBeers(query)
        },
        placeholder = { Text(text = "Search") },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ))

}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SingleItem(
    beer: Beer,
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager,
    navigator: DestinationsNavigator
) {
    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.White, containerColor = MyColors.TERTIARY.color
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = {
            keyboardController?.hide()
            focusManager.clearFocus()
            navigator.navigate(
                BeerSingleScreenDestination(
                    beer
                )
            )
        }
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = beer.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(16.dp))

            )
            Column(
                modifier = Modifier.padding(start = 12.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = beer.name,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = beer.description,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
                Text(
                    text = beer.abv + "% ABV",
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }


    }

}

