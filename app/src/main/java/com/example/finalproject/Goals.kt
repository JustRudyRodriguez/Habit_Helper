package com.example.finalproject

import android.icu.util.DateInterval
import java.time.LocalDateTime
import java.util.*

open class Goals(Name: String,Created: Date, Why: String) {
    //not sure if I need to assign these in kotlin, doing so just in case.
    val name = Name
    val created = Created
    val why = Why


    open fun Timesdone(perwhen:DateInterval){
        //returns the amount of times you worked on a task. between this time interval. DB Access.
    }

    open fun newEvent(When: LocalDateTime){
        //add to DB
    }
}

class regularity(Name: String,Created: Date, Why: String, When:Date): Goals(Name, Created, Why){



}
class frequency(Name: String,Created: Date, Why: String, Amount:Int, perWhen:DateInterval): Goals(Name, Created, Why){


}

class timeSpent(Name: String,Created: Date, Why: String, When:Date): Goals(Name, Created, Why){

}