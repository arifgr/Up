package com.example.up.data.remote

import com.example.up.data.remote.dto.CardDto
import retrofit2.http.GET

interface CardApi {

    @GET("wallets")
    suspend fun getCards(): List<CardDto>
}