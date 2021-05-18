package com.example.finalproject.Databasery

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy
//This dAO object is pretty much storing our SQL commands, only 1 is irregular.
@Dao
interface HabitDAO {
    @Insert(onConflict =OnConflictStrategy.IGNORE)
    suspend fun addHabit(habit: Habit)

    @Update
    suspend fun updateHabit(habit: Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Query("SELECT*FROM habit_table ORDER BY id DESC")
    fun getAllHabits(): LiveData<List<Habit>>

    @Query("DELETE FROM habit_table")
    suspend fun deleteAll()
    //need to fix this

    //This is for join searches. This allows us to view the events for a specific element.
    @Transaction
    @Query("SELECT * from habit_table where id = :id")//This may not be correct.
    fun getEventLists(id:Int):LiveData<List<jointInfo>>

    @Insert(onConflict =OnConflictStrategy.IGNORE)
    suspend fun addEvent(event: Event)

    @Update
    suspend fun updateEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("SELECT*FROM event_table ORDER BY Eid DESC")
    fun getAllEvent(): LiveData<List<Event>>

    @Query("DELETE FROM event_table")
    suspend fun deleteAllEvents()
    //need to fix this

    @Query("SELECT * FROM event_table where Goalid = :id")
    fun specificList(id :Int):LiveData<List<Event>>



}