package com.peter.bosta_assessment.cities_list.data.repo

import com.peter.bosta_assessment.cities_list.data.mappers.CityMapper
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.common.network.CityService
import javax.inject.Inject

class CityRepo @Inject constructor(
    private val cityService: CityService,
    private val cityMapper: CityMapper
) {
    suspend fun getCities(countryId: String): List<City>{
        val citiesDto = cityService.getCities(countryId)
        val mappedCities = cityMapper.mapListToModel(citiesDto.data)

        return mappedCities
    }
}