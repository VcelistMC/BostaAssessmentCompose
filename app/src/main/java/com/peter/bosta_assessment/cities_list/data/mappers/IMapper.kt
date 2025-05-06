package com.peter.bosta_assessment.cities_list.data.mappers

interface IMapper <MODEL, DTO> {
    fun mapToModel(dto: DTO): MODEL
    fun mapToDto(model: MODEL): DTO

    fun mapListToModel(dtos: List<DTO>): List<MODEL>{
        return dtos.map { dto -> mapToModel(dto) }
    }


    fun mapListToDto(models: List<MODEL>): List<DTO>{
        return models.map { model -> mapToDto(model) }
    }
}