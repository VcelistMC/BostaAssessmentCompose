package com.peter.bosta_assessment.cities_list.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.peter.bosta_assessment.cities_list.data.models.City

@Composable
fun CityItem(
    modifier: Modifier = Modifier,
    city: City
){
    Row(
        modifier = modifier
    ){
        Text(
            text = city.cityName
        )
        Icon
    }
}

@Preview
@Composable
fun CityItemPreview(){
    CityItem(city = City.mock1)
}