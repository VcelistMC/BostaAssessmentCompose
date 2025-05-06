package com.peter.bosta_assessment.cities_list.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.peter.bosta_assessment.R
import com.peter.bosta_assessment.cities_list.ui.composables.CityItem
import com.peter.bosta_assessment.cities_list.ui.composables.CitySearchField
import com.peter.bosta_assessment.cities_list.ui.composables.DistrictItem
import com.peter.bosta_assessment.cities_list.ui.intents.CitiesListIntent
import com.peter.bosta_assessment.cities_list.ui.states.CitiesListScreenState
import com.peter.bosta_assessment.cities_list.ui.viewmodels.CitiesListViewModel

@Composable
fun CitiesListScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<CitiesListViewModel>()
    val screenState = viewModel.citiesListState.collectAsState()

    CitiesListScreenContent(
        modifier = modifier
            .fillMaxSize(),
        citiesListState = screenState.value,
        onUserIntent = viewModel::onUserIntent
    )
}

@Composable
fun CitiesListScreenContent(
    modifier: Modifier = Modifier,
    citiesListState: CitiesListScreenState,
    onUserIntent: (CitiesListIntent) -> Unit
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
            onQueryChange = {
                onUserIntent(CitiesListIntent.SearchIntent(it))
            }
        )
        if(citiesListState.isLoading){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }else{
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {
                for (city in citiesListState.citiesList) {
                    val isExpanded = citiesListState.expandedCities.contains(city.cityId)
                    item{
                        CityItem(
                            city = city,
                            isExpanded = isExpanded,
                            onClick = {
                                onUserIntent(CitiesListIntent.ToggleCityIntent(city))
                            }
                        )
                    }
                    if(isExpanded){
                        items(city.districts){ district ->
                            DistrictItem(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.secondaryContainer)
                                    .padding(horizontal = 8.dp),
                                district = district
                            )
                        }
                    }
                }

            }
        }

    }
}