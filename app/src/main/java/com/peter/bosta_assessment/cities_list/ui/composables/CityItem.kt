package com.peter.bosta_assessment.cities_list.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.peter.bosta_assessment.R
import com.peter.bosta_assessment.cities_list.data.models.City

@Composable
fun CityItem(
    modifier: Modifier = Modifier,
    city: City,
    isExpanded: Boolean = true,
    onClick: () -> Unit
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.clickable { onClick() }.padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier.weight(1f),
                text = city.cityName
            )
            if(isExpanded){
                Icon(
                    painter = painterResource(R.drawable.caret_up),
                    contentDescription = ""
                )
            }else{
                Icon(
                    painter = painterResource(R.drawable.caret_down),
                    contentDescription = ""
                )
            }

        }
//        AnimatedVisibility(
//            visible = isExpanded
//        ) {
//            LazyColumn(
//                modifier = Modifier
//                    .background(color = MaterialTheme.colorScheme.secondaryContainer),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(city.districts){ district ->
//                    DistrictItem(
//                        modifier = Modifier.padding(horizontal = 8.dp),
//                        district = district
//                    )
//                }
//            }
//        }
    }

}
