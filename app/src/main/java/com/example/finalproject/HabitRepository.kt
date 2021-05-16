package com.example.finalproject

import androidx.lifecycle.LiveData

class HabitRepository(private val habitDAO: HabitDAO){
    val getAllHabits: LiveData<List<Habit>> = habitDAO.getAllHabits()

    suspend fun addHabit(habit: Habit){
        habitDAO.addHabit(habit)
    }

    suspend fun updateHabit(habit: Habit){
        habitDAO.updateHabit(habit)
    }
    suspend fun deleteHabit(habit: Habit){
        habitDAO.deleteHabit(habit)
    }

    suspend fun deleteAllHabit(){
        habitDAO.deleteAll()
    }
}