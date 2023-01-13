package com.example.up.data.remote.dto

import com.example.up.domain.model.Balance

data class BalanceDto(
    val currency: String?,
    val value: String?
)

fun BalanceDto.toBalance(): Balance {
    return Balance(
        currency = currency,
        value = value
    )
}