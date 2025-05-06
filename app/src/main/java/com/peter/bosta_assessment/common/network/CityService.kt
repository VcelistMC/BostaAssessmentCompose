package com.peter.bosta_assessment.common.network

import com.peter.bosta_assessment.cities_list.data.dtos.CityDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface CityService {
    @GET("cities/getAllDistricts")
    suspend fun getCities(
        @Query("countryId") countryId: String
    ): ListResponse<CityDTO>
}