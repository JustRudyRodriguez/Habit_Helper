package com.example.finalproject.Adapters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.finalproject.Databasery.Event
import com.example.finalproject.Databasery.HabitDatabase
import com.example.finalproject.Databasery.HabitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventViewModel (application: Application): AndroidViewModel(application) {
    //I think I only need 1 repo.
    private val repository: HabitRepository
    val getAllEvents: LiveData<List<Event>>

    init {
        val habitDAO = HabitDatabase.getDatabase(application).habitDAO()
        repository =
            HabitRepository(habitDAO)
        // I need to update this to only have events from selected Goal, leaving for now.
        // TODO: 5/18/2021
        getAllEvents = repository.getAllEvents
    }

    fun addEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEvent(event)
        }
    }

    fun updateEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEvent(event)
        }
    }

    fun deleteEvent(event: Event){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteEvent(event)
        }
    }
    //likely won't use this function.
    fun deleteAllEvent(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllEvent()
        }
    }

}