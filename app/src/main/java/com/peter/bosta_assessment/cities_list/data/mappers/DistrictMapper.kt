package com.peter.bosta_assessment.cities_list.data.mappers

import com.peter.bosta_assessment.cities_list.data.dtos.CityDTO
import com.peter.bosta_assessment.cities_list.data.dtos.DistrictDTO
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.cities_list.data.models.District
import javax.inject.Inject

class DistrictMapper @Inject constructor(): IMapper<District, DistrictDTO> {
    override fun mapToModel(dto: DistrictDTO): District {
        return District(
            zoneId = dto.zoneId,
            dropOffAvailability = dto.dropOffAvailability,
            pickupAvailability = dto.pickupAvailability,
            districtId = dto.districtId,
            districtName = dto.districtName,
            districtOtherName = dto.districtOtherName,
            zoneOtherName = dto.zoneOtherName,
            coverage = dto.coverage,
            zoneName = dto.zoneName
        )
    }

    override fun mapToDto(model: District): DistrictDTO {
        TODO("Not yet implemented")
    }
}