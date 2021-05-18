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
import com.example.finalproject.Databasery.Event
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_create_event.*
import kotlinx.android.synthetic.main.fragment_create_goal.*
import java.text.SimpleDateFormat
import java.util.*

//This fragment is where we create new events in our DB.
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


    val c = Calendar.getInstance()
    val df = SimpleDateFormat("MM-dd-yyyy")
    val formatDate: String = df.format(c.getTime())

    private lateinit var eventViewModel: EventViewModel
    //This is where we carry in our selected Habit to associate it correctly.
    private val args by navArgs<CreateEventArgs>()

    override fun onViewCreated(view: View, SavedINstanceState: Bundle?){
        //we load in our viewmodel to access our DB.
        eventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        //submission button.
        EventSubmit.setOnClickListener {
            addEventToDB()
        }

        //a back button to the eventlist page.
        BackToEvent.setOnClickListener{
            //this back button sends back our args, to make sure we remain on the same Habit.
            val action = CreateEventDirections.actionCreateEventToEventView(args.ForGoal)
            findNavController().navigate(action)
        }
        pickDateAndTime()



    }

    private fun addEventToDB(){

        //timestamp is from our habit function, we didn't include it in our entities for time.
        timestamp = "$cleanDate $cleanTime"

        //we check to make sure we have all the elements we need, and that time isn't negative.
        if(!(startime.isEmpty()||endtime.isEmpty()||formatDate.isEmpty()||(Calculations.isneg(startime,endtime)))){
            //create our event with the supplied vars.
            val event = Event(
                0,
                args.ForGoal.id,
                startime,
                endtime,
                formatDate//Using Type as a date holder now. Don't want to update DB format.
            )
            //update the DB via our viewmodel
            eventViewModel.addEvent( event)
            Toast.makeText(context,"Event Created Successfully!",Toast.LENGTH_SHORT).show()

            //bring user back to eventlist, while maintain the original habit as an arg.
            val action = CreateEventDirections.actionCreateEventToEventView(args.ForGoal)
            findNavController().navigate(action)
        }else{
            Toast.makeText(context,"Please enter valid Times",Toast.LENGTH_SHORT).show()
        }

    }


    //the following functions are similar to our createGoal functions, mostly for adjusting time to fit DB reqs.
    fun pickDateAndTime() {
        StartTimeBtn.setOnClickListener {
            getTimeCalandar()
            TimePickerDialog(context,this,hour,minute,true ).show()

        }

    }


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        cleanTime =
            Calculations.cleantime(hourOfDay, minute)

        if(flippy){
            Tv_startTime.text = "Start Time: $cleanTime"
            startime = cleanTime
            StartTimeBtn.text = "End Time"
            flippy = !flippy
        }else{
         tv_endTime.text = "End Time: $cleanTime"
            endtime = cleanTime
            StartTimeBtn.text = "Start Time"
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