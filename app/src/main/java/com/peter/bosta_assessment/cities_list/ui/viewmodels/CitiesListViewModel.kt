package com.peter.bosta_assessment.cities_list.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.cities_list.data.repo.CityRepo
import com.peter.bosta_assessment.cities_list.ui.intents.CitiesListIntent
import com.peter.bosta_assessment.cities_list.ui.states.CitiesListScreenState
import com.peter.bosta_assessment.common.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesListViewModel @Inject constructor(
    private val cityRepo: CityRepo
): BaseViewModel() {
    private var _citiesListState = MutableStateFlow(CitiesListScreenState())
    val citiesListState: StateFlow<CitiesListScreenState>
        get() = _citiesListState.asStateFlow()

    private var fullCityList = emptyList<City>()

    init {
        onUserIntent(CitiesListIntent.LoadAllCities)
    }
    private fun getCities(){
        viewModelScope.launch {
            _citiesListState.update {
                it.copy(isLoading = true)
            }
            val cities = cityRepo.getCities("60e4482c7cb7d4bc4849c4d5")
            _citiesListState.update {
                it.copy(isLoading = false, citiesList = cities)
            }
            fullCityList = cities
        }
    }

    private fun toggleDistrictVisibility(city: City){
        val ids = _citiesListState.value.expandedCities
        if(ids.contains(city.cityId)){
            ids.remove(city.cityId)
        }else{
            ids.add(city.cityId)
        }
    }

    private fun onSearchQueryChanged(query: String){
        _citiesListState.update {
            it.copy(searchQuery = query)
        }
    }

    fun onUserIntent(citiesListIntent: CitiesListIntent){
        when(citiesListIntent){
            is CitiesListIntent.SearchIntent -> {
                onSearchQueryChanged(citiesListIntent.query)
                searchCities(citiesListIntent.query)
            }
            is CitiesListIntent.ToggleCityIntent -> {
                toggleDistrictVisibility(citiesListIntent.city)
            }
            is CitiesListIntent.LoadAllCities -> getCities()
        }
    }

    private fun searchCities(text: String?) {
        val query = text?.lowercase()?.trim().orEmpty()

        if (query.isBlank()) {
            _citiesListState.update {
                it.copy(citiesList = fullCityList)
            }
            return
        }

        // filter cities where either the city or at least one district matches
        val matchingCities = fullCityList.filter { city ->
            city.cityName.lowercase().startsWith(query) ||
            city.districts.any { district ->
                district.districtName.lowercase().startsWith(query)
            }
        }

        val refinedResults = matchingCities.map { city ->
            val cityNameMatches = city.cityName.lowercase().startsWith(query)

            if (cityNameMatches) {
                // If the city name matches, keep it and all its districts
                city
            } else {
                // Otherwise, keep only the matching districts
                val matchingDistricts = city.districts.filter { district ->
                    district.districtName.lowercase().startsWith(query)
                }
                city.copy(districts = matchingDistricts)
            }
        }

        _citiesListState.update {
            it.copy(citiesList = refinedResults)
        }
    }
}
