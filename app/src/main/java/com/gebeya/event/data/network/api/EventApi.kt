package com.gebeya.event.data.network.api

import com.gebeya.event.data.network.model.EventEntity
import retrofit2.http.GET

interface EventApi {
    @GET("entries")
    suspend fun getEvent(): EventEntity

    //https://api.publicapis.org/entries
}