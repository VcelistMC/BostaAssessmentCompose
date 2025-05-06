package com.peter.bosta_assessment.cities_list.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.peter.bosta_assessment.R
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.cities_list.ui.composables.CityItem
import com.peter.bosta_assessment.cities_list.ui.composables.CitySearchField
import com.peter.bosta_assessment.cities_list.ui.states.CitiesListScreenState
import com.peter.bosta_assessment.cities_list.ui.viewmodels.CitiesListViewModel

@Composable
fun CitiesListScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<CitiesListViewModel>()
    val screenState = viewModel.citiesListState.collectAsState()

    LaunchedEffect(true) {
        viewModel.getCities()
    }

    CitiesListScreenContent(
        modifier = modifier
            .fillMaxSize(),
        citiesListState = screenState.value,
        onCityItemClicked = {},
        onSearchQueryChanged = {}
    )
}

@Composable
fun CitiesListScreenContent(
    modifier: Modifier = Modifier,
    citiesListState: CitiesListScreenState,
    onCityItemClicked: (City) -> Unit,
    onSearchQueryChanged: (String) -> Unit
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.choose_the_delivery_area),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        CitySearchField(
            query = citiesListState.searchQuery,
            onQueryChange = onSearchQueryChanged
        )
        if(citiesListState.isLoading){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }else{
            Column(
                modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
            ) {
                citiesListState.citiesList.forEach{ city ->
                    CityItem(
                        city = city,
                        isExpanded = citiesListState.expandedCities.contains(city.cityId),
                        onClick = {
                            onCityItemClicked(city)
                        }
                    )
                }
            }
        }

    }
}