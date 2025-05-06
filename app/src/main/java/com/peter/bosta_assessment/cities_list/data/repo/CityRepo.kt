package com.peter.bosta_assessment.cities_list.data.repo

import com.peter.bosta_assessment.cities_list.data.mappers.CityMapper
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.common.network.CityService
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class CityRepo @Inject constructor(
    private val cityService: CityService,
    private val cityMapper: CityMapper
) {
    private var fullCityList = emptyList<City>()

    suspend fun getCities(countryId: String): List<City>{
        val citiesDto = cityService.getCities(countryId)
        val mappedCities = cityMapper.mapListToModel(citiesDto.data)

        fullCityList = mappedCities
        return mappedCities
    }


    fun filterCities(query: String): List<City>{
        if (query.isBlank()) {
            return fullCityList
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

        return refinedResults
    }
}