package com.peter.bosta_assessment.cities_list.data.models

data class City(
    val cityId: String,
    val cityName: String,
    val cityOtherName: String,
    val cityCode: String,
    val districts: List<District>,
    val pickupAvailability: Boolean,
    val dropOffAvailability: Boolean,
){
    companion object{
        val mock1 = City(
            cityId = "Jrb6X6ucjiYgMP4T7",
            cityName = "Alexandria",
            cityOtherName = "الاسكندريه",
            cityCode = "EG-02",
            districts = District.mockList,
            pickupAvailability = true,
            dropOffAvailability = true,
        )

        val mock2 = City(
            cityId = "7mDPAohM3ArSZmWTm",
            cityName = "Assuit",
            cityOtherName = "اسيوط",
            cityCode = "EG-17",
            districts = District.mockList,
            pickupAvailability = true,
            dropOffAvailability = true,
        )

        val mockList = listOf(mock1, mock2)
    }
}
