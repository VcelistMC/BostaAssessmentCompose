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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.cities_list.ui.composables.CityItem
import com.peter.bosta_assessment.cities_list.ui.composables.CitySearchField

@Composable
fun CitiesListScreen(modifier: Modifier = Modifier, cityList: List<City>) {
    Scaffold { padding ->
        CitiesListScreenContent(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            cityList = cityList
        )
    }
}

@Composable
fun CitiesListScreenContent(
    modifier: Modifier = Modifier,
    cityList: List<City>,
){
    val configuration = LocalConfiguration.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
    ) {
        Text(
            text = "Choose the delivery area",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        CitySearchField(
            query = "",
            onQueryChange = {}
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            cityList.forEach{ city ->
                CityItem(
                    city = city,
                    isExpanded = true,
                    onClick = {

                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun CitiesListScreenContentPreview(){
    CitiesListScreenContent(
        cityList = City.mockList
    )
}