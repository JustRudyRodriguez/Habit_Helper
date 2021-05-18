package com.example.finalproject.Adapters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.finalproject.Databasery.Event
import com.example.finalproject.Databasery.HabitDatabase
import com.example.finalproject.Databasery.HabitRepository
import com.example.finalproject.Databasery.jointInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//This class is the main way fragments get event data from the database.
class EventViewModel (application: Application): AndroidViewModel(application) {
    //I think I only need 1 repo.
    private val repository: HabitRepository
    val getAllEvents: LiveData<List<Event>>

    var specifiedEvents: LiveData<List<Event>>

    init {
        val habitDAO = HabitDatabase.getDatabase(application).habitDAO()
        repository =
            HabitRepository(habitDAO)
        getAllEvents = repository.getAllEvents
        repository.specifyEvents(0)
        specifiedEvents =repository.getTheseEvents
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

    //This is what allows us to specify which goal we're looking for events from .
    fun updatelist(id:Int){
        repository.specifyEvents(id)
        specifiedEvents = repository.getTheseEvents
    }
}