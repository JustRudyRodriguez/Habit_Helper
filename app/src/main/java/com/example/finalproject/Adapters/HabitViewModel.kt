package com.example.finalproject.Adapters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.finalproject.Databasery.Habit
import com.example.finalproject.Databasery.HabitDatabase
import com.example.finalproject.Databasery.HabitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//This class will be used by the eventlist fragment.
class HabitViewModel (application: Application): AndroidViewModel(application) {
    private val repository: HabitRepository
    val getAllHabits: LiveData<List<Habit>>

    init {
        val habitDAO = HabitDatabase.getDatabase(application).habitDAO()
        repository =
            HabitRepository(habitDAO)

        getAllHabits = repository.getAllHabits
    }

    fun addHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHabit(habit)
        }
    }

    fun updateHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateHabit(habit)
        }
    }

    fun deleteHabit(habit: Habit){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteHabit(habit)
        }
    }
    //likely won't use this function.
    fun deleteAllHabit(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllHabit()
        }
    }

}