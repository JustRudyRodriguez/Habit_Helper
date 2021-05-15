package com.example.finalproject
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalTime

class Event(Date: LocalDateTime){
        val date = Date
    constructor(Date: LocalDateTime,DurationMinutes: Int) : this (Date){

       val duration = DurationMinutes.toDuration(DurationUnit.MINUTES)


        fun getTheDuration(): kotlin.time.Duration{
            return duration
        }
    }

    fun getThisDate(): LocalDateTime {
        return date
    }


}