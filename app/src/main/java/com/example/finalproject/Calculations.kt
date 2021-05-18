package com.example.finalproject

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
//some simple functions for converting time.
object Calculations {

    fun timeStampToString(timeStamp:Long):String{

        val stamp = Timestamp(timeStamp)
        //HH is 24 Hours, hh is 12.
        val formatted = SimpleDateFormat("MM/dd/yyyy HH:mm")
        val date:String = formatted.format((Date(stamp.time)))

        return date.toString()
    }
    fun getduration(t1:String,t2:String):String{
        val t3 :String

        val format = SimpleDateFormat("HH:mm")
        var d1= format.parse(t1)
        var d2 = format.parse(t2)

        var diff =  d2.time -d1.time
        var diffHours = diff/(60*60*1000)
        var diffMinutes = (diff/(60*1000))-(diffHours*60)

        t3 = cleantime(diffHours.toInt(),diffMinutes.toInt())
        return t3
    }
    fun isneg(t1:String,t2:String):Boolean{        val t3 :String

        val format = SimpleDateFormat("HH:mm")
        var d1= format.parse(t1)
        var d2 = format.parse(t2)

        var diff =  d2.time -d1.time
        return diff <= 0
    }

    fun calculateTimeBetweenDates(startdate:String):String{
        val endDate = timeStampToString(System.currentTimeMillis())

        val formatted = SimpleDateFormat("MM/dd/yyyy HH:mm")
        val date1 = formatted.parse(startdate)
        val date2 = formatted.parse(endDate)


        var isNegative = false

        var difference = date1.time - date2.time

        if(difference<0){
            difference = -(difference)
            isNegative = true
        }

        val minutes = difference /60/1000
        val hours = difference/60/1000/60
        val days = (difference/60/1000/60)/24
        val months = (difference/60/1000/60)/24/(365/12)
        val years = difference/60/1000/60/24/365

        if (isNegative){
            return when{
                minutes < 240 -> "starts in $minutes minutes"
                hours < 48 -> "starts in $hours minutes"
                days < 61 -> "starts in $days minutes"
                months < 24 -> "starts in $months minutes"
                else -> "starts in $years years"
            }


        }
        return when{
            minutes < 240 -> "$minutes minutes ago"
            hours < 48 -> "$hours minutes ago"
            days < 61 -> "$days minutes ago"
            months < 24 -> "$months minutes ago"
            else -> "$years years ago"
        }
    }

    fun cleanDate(day:Int,month:Int,year:Int):String{

        var _day = day.toString()
        var _month = month.toString()

        if (day <10){
            _day = "0$day"
        }
        if (month <9){
            _month = "0$month"
        }
        return "$_day/$_month/$year"

    }

    fun cleantime(hour:Int,minute:Int):String{

        var _hour = hour.toString()
        var _minute = minute.toString()

        if (hour <10){
            _hour = "0$hour"
        }
        if (minute <9){
            _minute = "0$minute"
        }
        return "$_hour:$_minute"

    }

}