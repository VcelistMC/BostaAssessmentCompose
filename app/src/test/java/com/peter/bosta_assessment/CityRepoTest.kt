package com.peter.bosta_assessment.cities_list.data.repo

import com.peter.bosta_assessment.cities_list.data.dtos.CityDTO
import com.peter.bosta_assessment.cities_list.data.mappers.CityMapper
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.common.network.CityService
import com.peter.bosta_assessment.common.network.ListResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class CityRepoTest {

    private val cityService = mock(CityService::class.java)
    private val cityMapper = mock(CityMapper::class.java)
    private val cityRepo = CityRepo(cityService, cityMapper)

    @Test
    fun `getCities should return mapped cities`() = runBlocking {
        val countryId = "country1"
        val cityDTO = CityDTO(
            cityId = "city1",
            cityCode = "code1",
            cityName = "City Name",
            cityOtherName = "Other Name",
            dropOffAvailability = true,
            pickupAvailability = false,
            districts = emptyList()
        )

        val city = City(
            cityId = "city1",
            cityCode = "code1",
            cityName = "City Name",
            cityOtherName = "Other Name",
            dropOffAvailability = true,
            pickupAvailability = false,
            districts = emptyList()
        )

        `when`(cityService.getCities(countryId)).thenReturn(ListResponse(success = "true", message = "", data = listOf(cityDTO)))
        `when`(cityMapper.mapListToModel(listOf(cityDTO))).thenReturn(listOf(city))

        val cities = cityRepo.getCities(countryId)

        assertEquals(1, cities.size)
        assertEquals(city, cities[0])
    }
}
