package com.peter.bosta_assessment

import com.peter.bosta_assessment.cities_list.data.mappers.DistrictMapper

import com.peter.bosta_assessment.cities_list.data.dtos.DistrictDTO
import com.peter.bosta_assessment.cities_list.data.models.District
import org.junit.Assert.assertEquals
import org.junit.Test

class DistrictMapperTest {

    private val districtMapper = DistrictMapper()

    @Test
    fun `mapToModel should correctly map DistrictDTO to District`() {
        val dto = DistrictDTO(
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

        val model = districtMapper.mapToModel(dto)

        assertEquals(dto.zoneId, model.zoneId)
        assertEquals(dto.dropOffAvailability, model.dropOffAvailability)
        assertEquals(dto.pickupAvailability, model.pickupAvailability)
        assertEquals(dto.districtId, model.districtId)
        assertEquals(dto.districtName, model.districtName)
        assertEquals(dto.districtOtherName, model.districtOtherName)
        assertEquals(dto.zoneOtherName, model.zoneOtherName)
        assertEquals(dto.coverage, model.coverage)
        assertEquals(dto.zoneName, model.zoneName)
    }

    @Test(expected = NotImplementedError::class)
    fun `mapToDto should throw NotImplementedError`() {
        val model = District(
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

        districtMapper.mapToDto(model)
    }
}
