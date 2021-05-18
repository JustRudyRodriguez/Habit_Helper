package com.example.finalproject.frags

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.finalproject.Adapters.EventViewModel
import com.example.finalproject.Calculations
import com.example.finalproject.frags.CreateEventDirections
import com.example.finalproject.Databasery.Event
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_create_event.*
import kotlinx.android.synthetic.main.fragment_create_goal.*
import kotlinx.coroutines.flow.callbackFlow
import java.util.*


class CreateEvent : Fragment(R.layout.fragment_create_event),
    TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener{

    private var startime = ""
    private var endtime =""
    private var drawableSelected = 0
    private var timestamp = ""

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var flippy = true

    private var cleanDate = ""
    private var cleanTime = ""

    private lateinit var eventViewModel: EventViewModel

    private val args by navArgs<CreateEventArgs>()

    override fun onViewCreated(view: View, SavedINstanceState: Bundle?){
        eventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)

        EventSubmit.setOnClickListener {
            addEventToDB()
        }

        pickDateAndTime()



    }

    private fun addEventToDB(){
        startime = Tv_startTime.text.toString()
        endtime = tv_endTime.text.toString()
        timestamp = "$cleanDate $cleanTime"
        if(!(startime.isEmpty()||endtime.isEmpty()||timestamp.isEmpty())){
            val event = Event(
                0,
                args.ForGoal.id,
                startime,
                endtime,
                "Temp Type"
            )
            eventViewModel.addEvent( event)
            Toast.makeText(context,"Event Created Successfully!",Toast.LENGTH_SHORT).show()
            val action = CreateEventDirections.actionCreateEventToEventView(args.ForGoal)
            findNavController().navigate(action)
        }else{
            Toast.makeText(context,"Please fill empty Fields",Toast.LENGTH_SHORT).show()
        }

    }

    fun pickDateAndTime() {
        StartTimeBtn.setOnClickListener {
            getTimeCalandar()
            TimePickerDialog(context,this,hour,minute,true).show()

        }

    }


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        cleanTime =
            Calculations.cleantime(hourOfDay, minute)

        if(flippy){
            Tv_startTime.text = "Start Time: $cleanTime"
            flippy = !flippy
        }else{
         tv_endTime.text = "End Times: $cleanTime"
        }


    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        cleanDate = Calculations.cleanDate(
            dayOfMonth,
            month,
            year
        )
        textView.text = "Date:$cleanDate"
    }

    private fun getTimeCalandar() {
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun getDateCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

    }
}