package com.example.up.data.remote.dto

import com.example.up.domain.model.PendingBalance

data class PendingBalanceDto(
    val currency: String?,
    val value: String?
)

fun PendingBalanceDto.toPendingBalance(): PendingBalance {
    return PendingBalance(
        currency = currency,
        value = value
    )
}