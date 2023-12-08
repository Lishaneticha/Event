package com.gebeya.event.data.db.model

import java.time.LocalDate

data class Events(
    val name: String,
    val location: String,
    val date: LocalDate,
    val image: Int,
    val id: Int = 0
)