package com.example.finalproject.Databasery

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class jointInfo (
    //Creates a one-to-many relationship with Goals and the events.
    @Embedded val habit: Habit,
    @Relation(
        parentColumn = "id",
        entityColumn = "Eid"
    )
    val eventList : List<Event>
)



