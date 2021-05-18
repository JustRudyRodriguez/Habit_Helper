package com.example.finalproject.Databasery

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Where we create the database object.
@Database(entities=[Habit::class, Event::class], version=1, exportSchema = false)
abstract class HabitDatabase : RoomDatabase(){
    //DAO allow database to access DAO object and queries.
    abstract fun habitDAO(): HabitDAO

    companion object{
        @Volatile
        private var INSTANCE: HabitDatabase?=null

        fun getDatabase(context: Context): HabitDatabase {
            val tempInstance =
                INSTANCE
            if(tempInstance!= null){
                return tempInstance
            }
            synchronized(this){
                //This creates the instance of the database.
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HabitDatabase::class.java,
                    "habit_database"
                ).build()

                INSTANCE =instance
                return instance
            }
        }
    }
}