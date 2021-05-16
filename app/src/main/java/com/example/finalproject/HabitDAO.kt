package com.example.finalproject

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy
@Dao
interface HabitDAO {
    @Insert(onConflict =OnConflictStrategy.IGNORE)
    suspend fun addHabit(habit:Habit)

    @Update
    suspend fun updateHabit(habit:Habit)

    @Delete
    suspend fun deleteHabit(habit:Habit)

    @Query("SELECT*FROM habit_table ORDER BY id DESC")
    fun getAllHabits(): LiveData<List<Habit>>

    @Query("DELETE FROM habit_table")
    suspend fun deleteAll()

}