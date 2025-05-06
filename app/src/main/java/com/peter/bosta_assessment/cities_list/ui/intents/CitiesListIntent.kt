package com.peter.bosta_assessment.cities_list.ui.intents

import com.peter.bosta_assessment.cities_list.data.models.City

sealed class CitiesListIntent {
    data object LoadAllCities: CitiesListIntent()
    data class SearchIntent(val query: String): CitiesListIntent()
    data class ToggleCityIntent(val city: City): CitiesListIntent()
}