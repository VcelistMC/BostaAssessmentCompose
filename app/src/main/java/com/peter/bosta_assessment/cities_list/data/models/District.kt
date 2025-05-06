package com.peter.bosta_assessment.cities_list.data.models

data class District (
    val zoneId: String,
    val zoneName: String,
    val zoneOtherName: String,
    val districtId: String,
    val districtName: String,
    val districtOtherName: String,
    val pickupAvailability: Boolean,
    val dropOffAvailability: Boolean,
    val coverage: String
){
    companion object {
        val mock1 =  District(
            zoneId = "9mih4NXL1GF",
            zoneName = "Abu Yousef",
            zoneOtherName = "ابو يوسف",
            districtId = "zoJP71_5Ca1",
            districtName = "Abu Yousef",
            districtOtherName = "ابو يوسف",
            pickupAvailability = true,
            dropOffAvailability = true,
            coverage = "BOSTA"
        )

        val mock2 = District(
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

        val mockList = listOf(mock1, mock2)
    }
}
