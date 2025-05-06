package com.peter.bosta_assessment.cities_list.ui.states

import com.peter.bosta_assessment.cities_list.data.models.City

data class CitiesListScreenState(
    val isLoading: Boolean = false,
    val citiesList: List<City> = emptyList(),
    val expandedCities: HashSet<String> = hashSetOf("Jrb6X6ucjiYgMP4T7"),
    val searchQuery: String = ""
)
