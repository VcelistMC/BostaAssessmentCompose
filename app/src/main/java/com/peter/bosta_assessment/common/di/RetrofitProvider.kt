package com.peter.bosta_assessment.common.di

import com.peter.bosta_assessment.common.network.CityService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitProvider {
    private const val baseUrl = "https://stg-app.bosta.co/api/v2/"


    @Singleton
    @Provides
    fun citiesRetrofitClient(): CityService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityService::class.java)
    }
}