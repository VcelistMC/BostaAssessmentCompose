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
            districts = listOf(
                District(
                    zoneId = "9mih4NXL1GF",
                    zoneName = "Abu Yousef",
                    zoneOtherName = "ابو يوسف",
                    districtId = "zoJP71_5Ca1",
                    districtName = "Abu Yousef",
                    districtOtherName = "ابو يوسف",
                    pickupAvailability = true,
                    dropOffAvailability = true,
                    coverage = "BOSTA"
                ),
                District(
                    zoneId = "9mih4NXL1GF",
                    zoneName = "Abu Yousef",
                    zoneOtherName = "ابو يوسف",
                    districtId = "Naw9Fm_UfMS",
                    districtName = "Qetaa ElTarik ElSahrawi",
                    districtOtherName = "قطاع الطريق الصحراوي",
                    pickupAvailability = true,
                    dropOffAvailability = true,
                    coverage = "BOSTA"
                )
            ),
            pickupAvailability = true,
            dropOffAvailability = true,
        )

        val mock2 = City(
            cityId = "7mDPAohM3ArSZmWTm",
            cityName = "Assuit",
            cityOtherName = "اسيوط",
            cityCode = "EG-17",
            districts = listOf(
                District(
                    zoneId = "FmVLOstjfZU",
                    zoneName = "Abanoub",
                    zoneOtherName = "ابنوب",
                    districtId = "rd7Eh9bAq_-",
                    districtName = "Abanoub",
                    districtOtherName = "ابنوب",
                    pickupAvailability = true,
                    dropOffAvailability = true,
                    coverage = "BOSTA"
                ),
                District(
                    zoneId = "oqeys3A1GmI",
                    zoneName = "Abu Teg",
                    zoneOtherName = "ابوتيج",
                    districtId = "pwa7SgQQU7N",
                    districtName = "Abu Teg",
                    districtOtherName = "ابوتيج",
                    pickupAvailability = true,
                    dropOffAvailability = true,
                    coverage = "BOSTA"
                )
            ),
            pickupAvailability = true,
            dropOffAvailability = true,
        )

        val mockList = listOf(mock1, mock2)
    }
}
