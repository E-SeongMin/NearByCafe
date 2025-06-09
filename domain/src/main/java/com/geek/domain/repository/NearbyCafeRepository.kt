package com.geek.domain.repository

import com.geek.domain.model.Cafe

interface NearbyCafeRepository {
    suspend fun getNearbyCafes(lat: Double, lon: Double): List<Cafe>
}