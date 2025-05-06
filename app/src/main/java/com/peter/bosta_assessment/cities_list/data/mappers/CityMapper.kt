package com.peter.bosta_assessment.cities_list.data.mappers

import com.peter.bosta_assessment.cities_list.data.dtos.CityDTO
import com.peter.bosta_assessment.cities_list.data.models.City
import javax.inject.Inject

class CityMapper @Inject constructor(
    private val districtMapper: DistrictMapper
): IMapper<City, CityDTO> {

    override fun mapToModel(dto: CityDTO): City {
        return City(
            cityId = dto.cityId,
            cityCode = dto.cityCode,
            cityName = dto.cityName,
            cityOtherName = dto.cityOtherName,
            dropOffAvailability = dto.dropOffAvailability,
            pickupAvailability = dto.pickupAvailability,
            districts = districtMapper.mapListToModel(dto.districts)
        )
    }

    override fun mapToDto(model: City): CityDTO {
        TODO("Not yet implemented")
    }
}