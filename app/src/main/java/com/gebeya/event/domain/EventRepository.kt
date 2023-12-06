package com.gebeya.event.domain

interface EventRepository {
    suspend fun getEvent()
    suspend fun postEvent()
    suspend fun patchEvent()
    suspend fun deleteEvent()
}