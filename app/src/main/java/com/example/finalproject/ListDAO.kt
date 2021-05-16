package com.example.finalproject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

class ListDAO {
    @Dao
    interface WordDao {

        @Query("SELECT * FROM list_table ORDER BY list ASC")
        fun getAlphabetizedWords(): List<List<Any?>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(word: List<Any?>)

        //Declares a suspend function to delete all the list
        @Query("DELETE FROM list_table")
        suspend fun deleteAll()
    }
}