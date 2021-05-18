package com.example.finalproject.Databasery

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
//This DB item is to join habits with elements.
//Creates a one-to-many relationship with Goals and the events.
@Entity
data class jointInfo (

    @Embedded val habit: Habit,
    @Relation(
        parentColumn = "id",
        entityColumn = "Eid"
    )
    val eventList : List<Event>
)



