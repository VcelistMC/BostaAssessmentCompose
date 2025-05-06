package com.peter.bosta_assessment.cities_list.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.peter.bosta_assessment.cities_list.data.mappers.CityMapper
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.cities_list.data.repo.CityRepo
import com.peter.bosta_assessment.common.network.CityService
import com.peter.bosta_assessment.common.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesListViewModel @Inject constructor(
    private val cityRepo: CityRepo
): BaseViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _cityList = MutableLiveData<List<City>>()
    val cityList: LiveData<List<City>>
        get() = _cityList

    private var fullCityList = emptyList<City>()

    fun getCities(){
        viewModelScope.launch {
            _isLoading.postValue(true)
            val cities = cityRepo.getCities("60e4482c7cb7d4bc4849c4d5")
            _isLoading.postValue(false)
            _cityList.postValue(cities)
            fullCityList = cities
        }
    }

    fun searchCities(text: String?) {
        val query = text?.lowercase()?.trim().orEmpty()

        if (query.isBlank()) {
            _cityList.postValue(fullCityList)
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

        _cityList.postValue(refinedResults)
    }
}
