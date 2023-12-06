package com.gebeya.event.data.network.repository

import com.gebeya.event.BaseClass
import com.gebeya.event.R
import com.gebeya.event.data.db.dao.EventDao
import com.gebeya.event.data.network.api.EventApi
import com.gebeya.event.domain.EventRepository

class EventRepositoryImpl(
    private val eventApi: EventApi,
    private val hello: String
): EventRepository {
    override suspend fun getEvent() {
//        val data = eventApi.getEvent()
        println("Network event data: $hello")
    }

    override suspend fun postEvent() {
        TODO("Not yet implemented")
    }

    override suspend fun patchEvent() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEvent() {
        TODO("Not yet implemented")
    }
}
