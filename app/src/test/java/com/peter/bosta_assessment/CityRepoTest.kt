package com.peter.bosta_assessment

import com.peter.bosta_assessment.cities_list.data.dtos.CityDTO
import com.peter.bosta_assessment.cities_list.data.dtos.DistrictDTO
import com.peter.bosta_assessment.cities_list.data.mappers.CityMapper
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.cities_list.data.models.District
import com.peter.bosta_assessment.cities_list.data.repo.CityRepo
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

    val countryId = "country1"
    val city1DTO = CityDTO(
        cityId = "C1",
        cityCode = "code1",
        cityName = "Cairo",
        cityOtherName = "Other Name",
        dropOffAvailability = true,
        pickupAvailability = false,
        districts = listOf(
            DistrictDTO(
                zoneId = "zone3",
                zoneName = "Z3",
                zoneOtherName = "zoneOtherName",
                districtId = "districtId",
                districtName = "districtName",
                districtOtherName = "districtOtherName",
                pickupAvailability = true,
                dropOffAvailability = true,
                coverage = "coverage"
            )
        )
    )

    val city1 = City(
        cityId = "C1",
        cityCode = "code1",
        cityName = "Cairo",
        cityOtherName = "Other Name",
        dropOffAvailability = true,
        pickupAvailability = false,
        districts = listOf(
            District(
                zoneId = "zone3",
                zoneName = "Z3",
                zoneOtherName = "zoneOtherName",
                districtId = "districtId",
                districtName = "districtName",
                districtOtherName = "districtOtherName",
                pickupAvailability = true,
                dropOffAvailability = true,
                coverage = "coverage"
            )
        )
    )

    val city2DDTO = CityDTO(
        cityId = "C2",
        cityCode = "code2",
        cityName = "City Name",
        cityOtherName = "Other Name",
        dropOffAvailability = true,
        pickupAvailability = false,
        districts = listOf(
            DistrictDTO(
                zoneId = "zone1",
                zoneName = "z1",
                zoneOtherName = "zoneOtherName",
                districtId = "districtId",
                districtName = "Cairo Airport",
                districtOtherName = "districtOtherName",
                pickupAvailability = true,
                dropOffAvailability = true,
                coverage = "coverage"
            )
        )
    )

    val city2 = City(
        cityId = "C2",
        cityCode = "code2",
        cityName = "City Name",
        cityOtherName = "Other Name",
        dropOffAvailability = true,
        pickupAvailability = false,
        districts = listOf(
            District(
                zoneId = "zone1",
                zoneName = "z1",
                zoneOtherName = "zoneOtherName",
                districtId = "districtId",
                districtName = "Cairo Airport",
                districtOtherName = "districtOtherName",
                pickupAvailability = true,
                dropOffAvailability = true,
                coverage = "coverage"
            )
        )
    )

    @Test
    fun `getCities should return mapped cities`() = runBlocking {

        `when`(cityService.getCities(countryId)).thenReturn(ListResponse(success = "true", message = "", data = listOf(city1DTO, city2DDTO)))
        `when`(cityMapper.mapListToModel(listOf(city1DTO, city2DDTO))).thenReturn(listOf(city1, city2))

        val cities = cityRepo.getCities(countryId)

        assertEquals(2, cities.size)
        assertEquals(city1, cities[0])
    }

    @Test
    fun `filterCities should return based on query`() = runBlocking {

        `when`(cityService.getCities(countryId)).thenReturn(ListResponse(success = "true", message = "", data = listOf(city1DTO, city2DDTO)))
        `when`(cityMapper.mapListToModel(listOf(city1DTO, city2DDTO))).thenReturn(listOf(city1, city2))

        cityRepo.getCities(countryId)

        val filtered = cityRepo.filterCities("cairo")

        assertEquals(2, filtered.size)
        assertEquals("Cairo", filtered[0].cityName)
        assertEquals("Cairo Airport", filtered[1].districts[0].districtName)
    }

    @Test
    fun `filterCities should return empty when nothing found`() = runBlocking {

        `when`(cityService.getCities(countryId)).thenReturn(ListResponse(success = "true", message = "", data = listOf(city1DTO, city2DDTO)))
        `when`(cityMapper.mapListToModel(listOf(city1DTO, city2DDTO))).thenReturn(listOf(city1, city2))

        cityRepo.getCities(countryId)


        val filtered = cityRepo.filterCities("none")

        assertEquals(0, filtered.size)

    }
}
