package com.example.up.data.repository

import com.example.up.data.remote.CardApi
import com.example.up.data.remote.dto.CardDto
import com.example.up.domain.repository.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val api: CardApi
) : CardRepository {
    override suspend fun getCards(): List<CardDto> {
        return api.getCards()
    }
}