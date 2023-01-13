package com.example.up.domain.model

data class Card(
    val balance: Balance?,
    val cvv: String?,
    val image: String?,
    val number: String?,
    val pendingBalance: PendingBalance?
)
