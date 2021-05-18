package com.example.finalproject.Databasery

import androidx.lifecycle.LiveData
import androidx.room.*
//Didn't end up using this.Thought I needed a seperate DAO.
@Dao
interface jointinfoDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHabit(event: Event)

    @Update
    suspend fun updateHabit(event: Event)

    @Delete
    suspend fun deleteHabit(event: Event)

    @Query("SELECT*FROM habit_table ORDER BY id DESC")
    fun getAllHabits(): LiveData<List<Habit>>

    @Query("DELETE FROM event_table")
    suspend fun deleteAll()
}
