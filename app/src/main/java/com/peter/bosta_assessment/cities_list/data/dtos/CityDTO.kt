package com.peter.bosta_assessment.cities_list.data.dtos

data class CityDTO(
    val cityId: String,
    val cityName: String,
    val cityOtherName: String,
    val cityCode: String,
    val districts: List<DistrictDTO>,
    val pickupAvailability: Boolean,
    val dropOffAvailability: Boolean,
)
