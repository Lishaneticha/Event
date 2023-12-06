package com.gebeya.event.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gebeya.event.data.network.repository.EventRepositoryImpl
import com.gebeya.event.domain.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val eventRepository: EventRepository
): ViewModel() {
    fun getEvent(){
        viewModelScope.launch{ eventRepository.getEvent() }
    }
}
