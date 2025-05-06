package com.peter.bosta_assessment.cities_list.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.cities_list.data.models.District

@Composable
fun DistrictItem(
    modifier: Modifier = Modifier,
    district: District
){
    Text(
        modifier = modifier.fillMaxWidth().padding(vertical = 12.dp),
        text = district.districtName
    )
}


@Preview
@Composable
fun DistrictItemPreview(){
    DistrictItem(district = City.mock1.districts.first())
}