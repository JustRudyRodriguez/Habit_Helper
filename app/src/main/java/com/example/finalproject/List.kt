package com.example.finalproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



    //each entity class represents a SQLite
    @Entity(tableName = "list_table")

    //every entity needs primary key
    //column info specifies the name of the column in the table
    class List<T>(@PrimaryKey @ColumnInfo(name = "list") val word: String)
