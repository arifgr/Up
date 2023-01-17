package com.example.up.data.remote.dto

import com.example.up.domain.model.Card

data class CardDto(
    val balance: BalanceDto?,
    val cvv: String?,
    val image: String?,
    val number: String?,
    val pendingBalance: PendingBalanceDto?
)

fun CardDto.toCard(): Card {
    return Card(
        balance = balance?.toBalance(),
        cvv = cvv,
        image = image,
        number = number,
        pendingBalance = pendingBalance?.toPendingBalance()
    )
}