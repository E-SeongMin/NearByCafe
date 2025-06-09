package com.geek.domain.usecase

import com.geek.domain.model.Cafe
import com.geek.domain.repository.NearbyCafeRepository

class GetNearbyCafesUseCase(
    private val repo: NearbyCafeRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double): List<Cafe> =
        repo.getNearbyCafes(lat, lon)
}