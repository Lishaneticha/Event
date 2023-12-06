package com.gebeya.event.data.network.model

import com.google.gson.annotations.SerializedName

data class EventEntity(
    val count: Int = 0,
    val entries: List<Entry> = emptyList()
)

data class Entry(
    @SerializedName("API")
    val apiGebeya: String = "",

    @SerializedName("Description")
    val desc: String = ""
)