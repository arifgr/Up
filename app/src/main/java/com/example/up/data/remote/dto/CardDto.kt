package com.example.up.data.remote.dto

import com.example.up.domain.model.Card

data class CardDto(
    val balanceDto: BalanceDto?,
    val cvv: String?,
    val image: String?,
    val number: String?,
    val pendingBalanceDto: PendingBalanceDto?
)

fun CardDto.toCard(): Card {
    return Card(
        balance = balanceDto?.toBalance(),
        cvv = cvv,
        image = image,
        number = number,
        pendingBalance = pendingBalanceDto?.toPendingBalance()
    )
}