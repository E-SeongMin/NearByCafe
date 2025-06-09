package com.geek.data.mapper

import com.geek.data.remote.CafeDto
import com.geek.domain.model.Cafe

object CafeMapper {
    fun fromDto(dto: CafeDto): Cafe = Cafe(
        name = dto.title,
        address = dto.address
    )
}