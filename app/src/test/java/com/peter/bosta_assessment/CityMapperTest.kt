package com.peter.bosta_assessment

import com.peter.bosta_assessment.cities_list.data.dtos.CityDTO
import com.peter.bosta_assessment.cities_list.data.dtos.DistrictDTO
import com.peter.bosta_assessment.cities_list.data.mappers.CityMapper
import com.peter.bosta_assessment.cities_list.data.mappers.DistrictMapper
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.cities_list.data.models.District
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class CityMapperTest {

    private val districtMapper = mock(DistrictMapper::class.java)
    private val cityMapper = CityMapper(districtMapper)

    @Test
    fun `mapToModel should correctly map CityDTO to City`() {
        val districtDTO = DistrictDTO(
            zoneId = "zone1",
            dropOffAvailability = true,
            pickupAvailability = false,
            districtId = "district1",
            districtName = "District Name",
            districtOtherName = "Other Name",
            zoneOtherName = "Zone Other Name",
            coverage = "Coverage",
            zoneName = "Zone Name"
        )

        val district = District(
            zoneId = "zone1",
            dropOffAvailability = true,
            pickupAvailability = false,
            districtId = "district1",
            districtName = "District Name",
            districtOtherName = "Other Name",
            zoneOtherName = "Zone Other Name",
            coverage = "Coverage",
            zoneName = "Zone Name"
        )

        `when`(districtMapper.mapListToModel(listOf(districtDTO))).thenReturn(listOf(district))

        val dto = CityDTO(
            cityId = "city1",
            cityCode = "code1",
            cityName = "City Name",
            cityOtherName = "Other Name",
            dropOffAvailability = true,
            pickupAvailability = false,
            districts = listOf(districtDTO)
        )

        val model = cityMapper.mapToModel(dto)

        assertEquals(dto.cityId, model.cityId)
        assertEquals(dto.cityCode, model.cityCode)
        assertEquals(dto.cityName, model.cityName)
        assertEquals(dto.cityOtherName, model.cityOtherName)
        assertEquals(dto.dropOffAvailability, model.dropOffAvailability)
        assertEquals(dto.pickupAvailability, model.pickupAvailability)
        assertEquals(1, model.districts.size)
        assertEquals(district, model.districts[0])
    }

    @Test(expected = NotImplementedError::class)
    fun `mapToDto should throw NotImplementedError`() {
        val model = City(
            cityId = "city1",
            cityCode = "code1",
            cityName = "City Name",
            cityOtherName = "Other Name",
            dropOffAvailability = true,
            pickupAvailability = false,
            districts = emptyList()
        )

        cityMapper.mapToDto(model)
    }
}
