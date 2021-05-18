package com.example.finalproject.Databasery


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="habit_table")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val habit_title: String,
    val habit_description:String,
    val habit_startTime:String,
    val type:String
)