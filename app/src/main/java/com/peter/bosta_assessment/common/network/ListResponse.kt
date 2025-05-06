package com.peter.bosta_assessment.common.network

data class ListResponse <T> (
    val success: String,
    val message: String,
    val data: List<T>
)