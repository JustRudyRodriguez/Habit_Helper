package com.example.finalproject.Databasery

import androidx.lifecycle.LiveData

class HabitRepository(private val habitDAO: HabitDAO) {
    val getAllHabits: LiveData<List<Habit>> = habitDAO.getAllHabits()
    val getAllEvents: LiveData<List<Event>> = habitDAO.getAllEvent()
    suspend fun addHabit(habit: Habit) {
        habitDAO.addHabit(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        habitDAO.updateHabit(habit)
    }

    suspend fun deleteHabit(habit: Habit) {
        habitDAO.deleteHabit(habit)
    }

    suspend fun deleteAllHabit() {
        habitDAO.deleteAll()
    }

    suspend fun addEvent(event: Event) {
        habitDAO.addEvent(event)
    }


    suspend fun updateEvent(event: Event) {
        habitDAO.updateEvent(event)
    }


    suspend fun deleteEvent(event: Event) {
        habitDAO.deleteEvent(event)
    }

    //likely won't use this function.
    suspend fun deleteAllEvent() {
        habitDAO.deleteAllEvents()
    }

}