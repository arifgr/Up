package com.example.up.domain.repository

import com.example.up.data.remote.dto.CardDto

interface CardRepository {
    suspend fun getCards(): List<CardDto>
}