package com.gebeya.event.di

import com.gebeya.event.BaseClass
import com.gebeya.event.data.network.api.EventApi
import com.gebeya.event.data.network.repository.EventRepositoryImpl
import com.gebeya.event.domain.EventRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEventApi(): EventApi{
        return Retrofit.Builder()
            .baseUrl("https://api.publicapis.org/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(EventApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEventRepository(eventApi: EventApi, @Named("val 2") a: String): EventRepository{
        return EventRepositoryImpl(eventApi, a)
    }

    @Provides
    @Singleton
    @Named("val 1")
    fun provideString(): String{
        return "Hello from Gebeya class"
    }


    @Provides
    @Singleton
    @Named("val 2")
    fun provideString2(): String{
        return "Hello from kotlin"
    }
    //https://api.publicapis.org/entries
}