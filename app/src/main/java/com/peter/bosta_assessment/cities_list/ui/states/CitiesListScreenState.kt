package com.peter.bosta_assessment.cities_list.ui.states

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.peter.bosta_assessment.cities_list.data.models.City

data class CitiesListScreenState(
    val isLoading: Boolean = false,
    val citiesList: List<City> = emptyList(),
    val expandedCities: SnapshotStateList<String> = mutableStateListOf(),
    val searchQuery: String = ""
)
