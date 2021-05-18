package com.example.finalproject.Databasery


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


//the DB entitiy for our events.
@Parcelize
@Entity(tableName="event_table")
data class Event(
    @PrimaryKey(autoGenerate = true)
    //need to make this a composite key to attach to goals.
    val Eid: Int,
    val Goalid: Int,
    val Event_startTime:String,
    val Event_endTime:String,
    val type:String
    //gonna want to add a spinner for type
    // TODO: 5/18/2021
//gonna want to add a date.
): Parcelable